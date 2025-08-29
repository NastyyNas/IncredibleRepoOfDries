provider "google" {
  project = "spinnaker-450015"
  region  = "europe-west1"
}

resource "google_compute_network" "vpc" {
  name                    = "my-vpc"
  auto_create_subnetworks = false
}

resource "google_compute_subnetwork" "private_1" {
  name                     = "private-1-subnet"
  ip_cidr_range            = "10.0.1.0/24"
  region                   = "europe-west1"
  network                  = google_compute_network.vpc.id
  private_ip_google_access = true
}

resource "google_compute_subnetwork" "private_2" {
  name                     = "private-2-subnet"
  ip_cidr_range            = "10.0.2.0/24"
  region                   = "europe-west1"
  network                  = google_compute_network.vpc.id
  private_ip_google_access = true
}

resource "google_compute_global_address" "private_ip_range" {
  name          = "google-managed-services-my-vpc"
  purpose       = "VPC_PEERING"
  address_type  = "INTERNAL"
  prefix_length = 16
  network       = google_compute_network.vpc.id
}

resource "google_service_networking_connection" "private_vpc_connection" {
  network                 = google_compute_network.vpc.id
  service                 = "servicenetworking.googleapis.com"
  reserved_peering_ranges = [google_compute_global_address.private_ip_range.name]
}

resource "google_compute_firewall" "allow_mysql" {
  name    = "allow-mysql"
  network = google_compute_network.vpc.name

  allow {
    protocol = "tcp"
    ports    = ["3306"]
  }

  source_ranges = ["10.0.1.0/24"] # Your subnet CIDR
  # No target_tags needed since Cloud SQL is a managed service, not a VM
}

resource "google_compute_firewall" "allow_ssh" {
  name    = "allow-ssh"
  network = google_compute_network.vpc.name

  allow {
    protocol = "tcp"
    ports    = ["22", "80", "3000"]
  }

  source_ranges = ["0.0.0.0/0"] # Open to all; restrict to your IP for better security
}

resource "google_storage_bucket" "bucket" {
  name          = "bucket-lab-11904402"
  location      = "US"
  force_destroy = true
}

resource "google_storage_bucket_object" "images" {
  for_each = fileset("${path.module}/images", "**/*.jpg")

  name         = each.value
  bucket       = google_storage_bucket.bucket.name
  source       = "${path.module}/images/${each.value}"
  content_type = "image/jpeg"
}

resource "google_sql_database_instance" "mysql_instance" {
  name             = "my-mysql-instance"
  region           = "europe-west1"
  database_version = "MYSQL_8_0"

  depends_on = [google_service_networking_connection.private_vpc_connection]

  settings {
    tier = "db-f1-micro"
    ip_configuration {
      ipv4_enabled    = false
      private_network = google_compute_network.vpc.id
    }
  }
  deletion_protection = false
}

resource "google_sql_user" "default" {
  name     = "dbuser"
  instance = google_sql_database_instance.mysql_instance.name
  password = "changeme123"
}

resource "google_sql_database" "default" {
  name     = "todo"
  instance = google_sql_database_instance.mysql_instance.name
}

# Instance Template for Backend
resource "google_compute_instance_template" "backend_template" {
  name         = "backend-template"
  machine_type = "e2-micro"
  region       = "europe-west1"

  disk {
    source_image = "debian-cloud/debian-11"
    auto_delete  = true
    boot         = true
  }

  network_interface {
    network    = google_compute_network.vpc.id
    subnetwork = google_compute_subnetwork.private_1.id
    access_config {}
  }

  metadata_startup_script = <<-EOT
    #!/bin/bash
    apt-get update -y
    curl -sL https://deb.nodesource.com/setup_20.x | bash
    apt -y install nodejs
    apt -y install git
    git clone https://github.com/NastyyNas/CloudToDoApp.git
    cd CloudToDoApp/backend/
    git checkout sequelize
    sed -i "s/host: process.env.DBURL || 'localhost'/host: '${google_sql_database_instance.mysql_instance.private_ip_address}'/" db.js
    sed -i "s/username: process.env.DBUSER || 'root'/username: '${google_sql_user.default.name}'/" db.js
    sed -i "s/password: process.env.DBPASSWORD || ''/password: '${google_sql_user.default.password}'/" db.js
    sed -i "s/database: process.env.DBDATABASE || 'todo'/database: '${google_sql_database.default.name}'/" db.js
    cd routes
    sed -i 's/process\.env\.BUCKET/"${google_storage_bucket.bucket.name}"/g' carrousel.routes.js
    cd ..
    npm install @google-cloud/storage
    npm install
    npm start
  EOT

  service_account {
    email  = "spinnaker-gce-account@spinnaker-450015.iam.gserviceaccount.com"
    scopes = ["https://www.googleapis.com/auth/cloud-platform"]
  }

  tags = ["backend"]
}

# Managed Instance Group for Backend
resource "google_compute_region_instance_group_manager" "backend_group" {
  name               = "backend-group"
  region             = "europe-west1"
  base_instance_name = "backend"
  version {
    instance_template = google_compute_instance_template.backend_template.id
  }
  target_size = 2
  named_port {
    name = "http"
    port = 3000
  }
  auto_healing_policies {
    health_check      = google_compute_health_check.backend_health_check.id
    initial_delay_sec = 300
  }
}

# Health Check for Backend
resource "google_compute_health_check" "backend_health_check" {
  name               = "backend-health-check"
  check_interval_sec = 5
  timeout_sec        = 5
  healthy_threshold  = 2
  unhealthy_threshold = 2

  http_health_check {
    port = 3000
    request_path = "/health"
  }
}

# Firewall Rule for Backend HTTP
resource "google_compute_firewall" "allow_backend_http" {
  name    = "allow-backend-http"
  network = google_compute_network.vpc.name

  allow {
    protocol = "tcp"
    ports    = ["3000"]
  }

  target_tags   = ["backend"]
  source_ranges = ["0.0.0.0/0"]
}

# Backend Service for Load Balancer
resource "google_compute_backend_service" "backend_service" {
  name                  = "backend-service"
  protocol              = "HTTP"
  port_name             = "http"
  timeout_sec           = 10
  health_checks         = [google_compute_health_check.backend_health_check.id]
  backend {
    group = google_compute_region_instance_group_manager.backend_group.instance_group
  }
}

# URL Map for Load Balancer
resource "google_compute_url_map" "backend_url_map" {
  name            = "backend-url-map"
  default_service = google_compute_backend_service.backend_service.id
}

# HTTP Proxy for Load Balancer
resource "google_compute_target_http_proxy" "backend_http_proxy" {
  name    = "backend-http-proxy"
  url_map = google_compute_url_map.backend_url_map.id
}

# Reserve a Global IP for Load Balancer
resource "google_compute_global_address" "backend_lb_ip" {
  name = "backend-lb-ip"
}

# Global Forwarding Rule for Load Balancer
resource "google_compute_global_forwarding_rule" "backend_forwarding_rule" {
  name                  = "backend-forwarding-rule"
  target                = google_compute_target_http_proxy.backend_http_proxy.id
  port_range            = "3000"
  load_balancing_scheme = "EXTERNAL"
  ip_protocol           = "TCP"
  ip_address            = google_compute_global_address.backend_lb_ip.address
}

# Autoscaler for Backend Group
resource "google_compute_region_autoscaler" "backend_autoscaler" {
  name   = "backend-autoscaler"
  region = "europe-west1"
  target = google_compute_region_instance_group_manager.backend_group.id

  autoscaling_policy {
    max_replicas    = 2
    min_replicas    = 1
    cpu_utilization {
      target = 0.6
    }
  }
}

output "backend_load_balancer_ip" {
  value = google_compute_global_address.backend_lb_ip.address
}

resource "google_compute_instance" "frontend" {
  name         = "my-frontend"
  machine_type = "e2-micro"
  zone         = "europe-west1-b"

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-11"
    }
  }

  network_interface {
    network    = google_compute_network.vpc.id
    subnetwork = google_compute_subnetwork.private_2.id
    access_config {} # Comment this line if you want no external IP
  }

  metadata_startup_script = <<-EOT
    #!/bin/bash
    apt-get update -y
    curl -sL https://deb.nodesource.com/setup_20.x | bash
    apt -y install nodejs
    apt -y install git nginx
    git clone https://github.com/NastyyNas/CloudToDoApp.git
    cd CloudToDoApp/frontend/
    git checkout sequelize
    echo "export const environment = {production: true, apiurl: 'http://${google_compute_global_address.backend_lb_ip.address}:3000'};" > src/environments/environment.prod.ts
    npm install
    npm run build --configuration=production
    rm -rf /usr/share/nginx/html
    cp -r dist/frontend/ /usr/share/nginx/html/
    cp nginx.conf /etc/nginx/nginx.conf
    sudo systemctl enable nginx
    sudo systemctl start nginx
  EOT

  service_account {
    email = "spinnaker-gce-account@spinnaker-450015.iam.gserviceaccount.com"
    scopes = ["https://www.googleapis.com/auth/cloud-platform"]
  }
}

output "mysql_private_ip" {
  value = google_sql_database_instance.mysql_instance.private_ip_address
}
