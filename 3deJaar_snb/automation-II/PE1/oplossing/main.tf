provider "aws" {
  region = "us-west-2"
}

resource "aws_vpc" "main" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_support   = true
  enable_dns_hostnames = true

  tags = {
    Name = "MijnApp VPC"
  }
}

resource "tls_private_key" "bastion_ssh_key" {
  algorithm = "RSA"
  rsa_bits  = 4096
}

resource "aws_key_pair" "generated_key" {
  key_name   = "PE_bastion_key"
  public_key = tls_private_key.bastion_ssh_key.public_key_openssh

  provisioner "local-exec" {
    command = <<-EOT
      echo '${tls_private_key.bastion_ssh_key.private_key_pem}' > ./PE_bastion_key.pem
      chmod 400 ./PE_bastion_key.pem
    EOT
  }

  provisioner "local-exec" {
    command = "rm -f ./PE_bastion_key.pem"
    when    = destroy
  }
}

resource "aws_internet_gateway" "main" {
  vpc_id = aws_vpc.main.id

  tags = {
    Name = "main-igw"
  }
}

resource "aws_subnet" "public_1" {
  vpc_id                  = aws_vpc.main.id
  cidr_block              = "10.0.1.0/24"
  map_public_ip_on_launch = true
  availability_zone       = "us-west-2a"

  tags = {
    Name = "public-subnet-1"
  }
}

resource "aws_subnet" "public_2" {
  vpc_id                  = aws_vpc.main.id
  cidr_block              = "10.0.2.0/24"
  map_public_ip_on_launch = true
  availability_zone       = "us-west-2b"

  tags = {
    Name = "public-subnet-2"
  }
}

resource "aws_subnet" "private_1" {
  vpc_id            = aws_vpc.main.id
  cidr_block        = "10.0.3.0/24"
  availability_zone = "us-west-2a"

  tags = {
    Name = "private-subnet-1"
  }
}

resource "aws_subnet" "private_2" {
  vpc_id            = aws_vpc.main.id
  cidr_block        = "10.0.4.0/24"
  availability_zone = "us-west-2b"

  tags = {
    Name = "private-subnet-2"
  }
}

resource "aws_route_table" "public" {
  vpc_id = aws_vpc.main.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.main.id
  }

  tags = {
    Name = "public-route-table"
  }
}

resource "aws_route_table_association" "public_1" {
  subnet_id      = aws_subnet.public_1.id
  route_table_id = aws_route_table.public.id
}

resource "aws_route_table_association" "public_2" {
  subnet_id      = aws_subnet.public_2.id
  route_table_id = aws_route_table.public.id
}

resource "aws_eip" "nat" {
  domain = "vpc"
  tags = {
    Name = "aws_eip"
  }
}

resource "aws_nat_gateway" "nat" {
  allocation_id = aws_eip.nat.allocation_id
  subnet_id     = aws_subnet.public_1.id

  tags = {
    Name = "nat-gateway"
  }
}

resource "aws_route_table" "private" {
  vpc_id = aws_vpc.main.id

  route {
    cidr_block     = "0.0.0.0/0"
    nat_gateway_id = aws_nat_gateway.nat.id
  }

  tags = {
    Name = "private-route-table"
  }
}

resource "aws_route_table_association" "private_1" {
  subnet_id      = aws_subnet.private_1.id
  route_table_id = aws_route_table.private.id
}

resource "aws_route_table_association" "private_2" {
  subnet_id      = aws_subnet.private_2.id
  route_table_id = aws_route_table.private.id
}

data "http" "my_ip" {
  url = "http://ipinfo.io/ip"
}

resource "aws_security_group" "bastion_sg" {
  name = "bastion_sg"
  vpc_id = aws_vpc.main.id

  ingress {
    description = "Allow SSH"
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["${data.http.my_ip.response_body}/32"]
  }
}

resource "aws_security_group" "lb_sg" {
  name   = "lb-sg"
  vpc_id = aws_vpc.main.id

  ingress {
    description = "Allow HTTP"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    description = "Allow HTTP"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

   tags = {
    Name = "load-balancer-security-group"
  }
}

resource "aws_security_group" "web_sg" {
  name_prefix = "web-sg-"
  vpc_id      = aws_vpc.main.id

  ingress {
    description = "Allow HTTP"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "only allow SSH from bastion"
    from_port = 22
    to_port = 22
    protocol = "tcp"
    security_groups = [aws_security_group.bastion_sg.id]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "web-security-group"
  }
}

resource "aws_security_group_rule" "name" {
  type = "egress"
  from_port = 22
  to_port = 22
  protocol = "tcp"
  source_security_group_id = aws_security_group.web_sg.id
  security_group_id = aws_security_group.bastion_sg.id
}

resource "aws_launch_template" "web_template" {
  name_prefix          = "web-template-"
  image_id             = "ami-05134c8ef96964280"
  instance_type        = "t2.micro"
  vpc_security_group_ids = [aws_security_group.web_sg.id]
  key_name = aws_key_pair.generated_key.key_name
  
  iam_instance_profile {
    name = "LabInstanceProfile"
  }
  

  user_data = base64encode(<<-EOF
              #!/bin/bash
              sudo apt update
              yes | sudo apt install nodejs
              sudo apt install git
              git clone https://github.com/PXL-Automation-II/nodejs-pxl.git
              cd nodejs-pxl
              yes | sudo apt install npm
              npm install express --save
              sudo BACKEND_PORT=80 node app.js
              EOF
            )
  
  tags = {
    Name = "web-server"
  }
}

resource "aws_instance" "bastion" {
  ami = "ami-05134c8ef96964280"
  instance_type = "t2.micro"
  vpc_security_group_ids = [aws_security_group.bastion_sg.id]
  subnet_id = aws_subnet.public_1.id
  key_name = aws_key_pair.generated_key.key_name
  provisioner "file" {
    source = "./PE_bastion_key.pem"
    destination = "./PE_bastion_key.pem"

    connection {
      type = "ssh"
      user = "ubuntu"
      private_key = tls_private_key.bastion_ssh_key.private_key_pem
      host = self.public_ip
    }
  }

  tags = {
    Name = "bastion-host"
  }
}

resource "aws_autoscaling_group" "web_asg" {
  launch_template {
    id      = aws_launch_template.web_template.id
    version = "$Latest"
  }

  target_group_arns = [aws_lb_target_group.web_tg.arn]
  health_check_type = "ELB"

  vpc_zone_identifier = [aws_subnet.private_1.id, aws_subnet.private_1.id]

  max_size = 4
  min_size = 2


  tag {
    key                 = "Name"
    value               = "web-server"
    propagate_at_launch = true
  }
}

resource "aws_lb" "web_lb" {
  name               = "web-lb"
  load_balancer_type = "application"
  security_groups    = [aws_security_group.lb_sg.id]
  subnets            = [aws_subnet.public_1.id, aws_subnet.public_2.id]
}

resource "aws_lb_target_group" "web_tg" {
  name     = "web-tg"
  port     = 80
  protocol = "HTTP"
  vpc_id   = aws_vpc.main.id

  health_check {
    path                = "/"
    protocol            = "HTTP"
    matcher             = "200"
    interval            = 15
    timeout             = 3
    healthy_threshold   = 2
    unhealthy_threshold = 2
  }
}

resource "aws_lb_listener" "web_listener" {
  load_balancer_arn = aws_lb.web_lb.arn
  port              = 80
  protocol          = "HTTP"

  default_action {
    type             = "fixed-response"

    fixed_response {
      content_type = "text/plain"
      message_body = "404: page not found"
      status_code = 404
    }
  }
}

resource "aws_lb_listener_rule" "asg" {
  listener_arn = aws_lb_listener.web_listener.arn
  priority     = 100
  condition {
    path_pattern {
      values = ["*"]
    }
  }
  action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.web_tg.arn
  }
}

output "aws_subnets" {
  value       = [aws_subnet.private_1.cidr_block, aws_subnet.private_2.cidr_block]
  description = "IP Private subnets"
}

# Output for VPC ID
output "vpc_id" {
  description = "The ID of the VPC"
  value       = aws_vpc.main.id
}

# Output for VPC Tag
output "vpc_tag" {
  description = "The tag of the VPC"
  value       = aws_vpc.main.tags["Name"]
}

# Output for Internet Gateway ID
output "internet_gateway_id" {
  description = "The ID of the Internet Gateway"
  value       = aws_internet_gateway.main.id
}

output "load_balancer_dns" {
  description = "The ip address of the loadbalancer"
  value       = aws_lb.web_lb.dns_name
}


data "aws_availability_zones" "available" {
  state = "available"
}

data "aws_availability_zones" "unavailable" {
  state = "unavailable"
}

output "az_names_and_status" {
  value = merge(
    {for az in data.aws_availability_zones.available.names : az => "available"},
    {for az in data.aws_availability_zones.unavailable.names : az => "unavailable"}
  )
  description = "availability zones"
}

output "subnet_ids" {
  value = {
    private = [aws_subnet.private_1.id, aws_subnet.private_2.id]
    public  = [aws_subnet.public_1.id, aws_subnet.public_2.id]
  }
  description = "id's of the subnets"
}

output "security_groups" {
  description = "the id and name of the security groups"
  value = [
    {
      id   = aws_security_group.web_sg.id
      name = aws_security_group.web_sg.name
    },
    {
      id   = aws_security_group.lb_sg.id
      name = aws_security_group.lb_sg.name
    }
  ]
}

output "bastion_ip" {
  description = "Bastion IP"
  value = aws_instance.bastion.public_ip
}

output "my_ip" {
  description = "my ip"
  value = data.http.my_ip.response_body
}
