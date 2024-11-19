locals {
    backend_port = 3000
    http_port = 80
    sql_port = 3306
}

# Security groups
###################################################################



resource "aws_security_group" "sg-backend" {
  name = "backend-sg"
  vpc_id = var.vpc_id

  ingress {
    description = "Allow mysql"
    from_port = local.sql_port
    to_port = local.sql_port
    protocol = "tcp"
    cidr_blocks = [ "0.0.0.0/0" ]
  }

  ingress {
    description = "Allow HTTP"
    from_port = local.http_port
    to_port = local.http_port
    protocol = "tcp"
    cidr_blocks = [ "0.0.0.0/0" ]
  }

  ingress {
    description = "Allow HTTPS"
    from_port = 443
    to_port = 443
    protocol = "tcp"
    cidr_blocks = [ "0.0.0.0/0" ]
  }

  ingress {
    description = "Allow backend port"
    from_port = local.backend_port
    to_port = local.backend_port
    protocol = "tcp"
    cidr_blocks = [ "0.0.0.0/0" ]
  }

  egress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = [ "0.0.0.0/0" ]
  }

  tags = {
    Name = "backend-sg"
  }
}

resource "aws_security_group" "sg-frontend" {
  name = "frontend-sg"
  vpc_id = var.vpc_id

  ingress {
    description = "Allow backend port"
    from_port = local.backend_port
    to_port = local.backend_port
    protocol = "tcp"
    security_groups = [aws_security_group.sg-backend.id]
  }

  ingress {
    description = "Allow HTTP"
    from_port = local.http_port
    to_port = local.http_port
    protocol = "tcp"
    cidr_blocks = [ "0.0.0.0/0" ]
  }

  ingress {
    description = "Allow HTTP"
    from_port = 443
    to_port = 443
    protocol = "tcp"
    cidr_blocks = [ "0.0.0.0/0" ]
  }

  egress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = [ "0.0.0.0/0" ]
  }

  tags = {
    Name = "frontend-sg"
  }
}

# Instances
###################################################################


resource "aws_launch_template" "backend-template" {
  name_prefix          = "backend-lt-${var.env}"
  image_id             = "ami-06b21ccaeff8cd686"
  instance_type        = "t2.micro"
  vpc_security_group_ids = [aws_security_group.sg-backend.id]
  iam_instance_profile {
    name = "LabInstanceProfile"
  }
  user_data = base64encode(var.user_data_backend)
  tags = {
    Name = "backend-lt"
  }
}

resource "aws_launch_template" "frontend-template" {
  name_prefix          = "frontend-lt-${var.env}"
  image_id             = "ami-06b21ccaeff8cd686"
  instance_type        = "t2.micro"
  vpc_security_group_ids = [aws_security_group.sg-frontend.id]
  iam_instance_profile {
    name = "LabInstanceProfile"
  }
  user_data = base64encode(<<-EOF
              #!/bin/bash
              yum update -y
              yum install -y git nodejs nginx
              git clone https://github.com/2TIN-CloudServices1/CloudToDoApp.git
              cd CloudToDoApp/frontend/
              git checkout ${var.branch}
              echo "export const environment = {production: true, apiurl: 'http://${aws_lb.lb-backend.dns_name}:3000'};" > src/environments/environment.prod.ts
              npm install
              npm run build --configuration=production
              rm -rf /usr/share/nginx/html
              cp -r dist/frontend/ /usr/share/nginx/html/
              cp nginx.conf /etc/nginx/nginx.conf
              sudo systemctl enable nginx
              sudo systemctl start nginx    
              EOF
              )
  tags = {
    Name = "frontend-lt"
  }
}

# Auto-scaling
###################################################################

resource "aws_autoscaling_group" "frontend-asg" {
  launch_template {
    id = aws_launch_template.frontend-template.id
    version = "$Latest"
  }

  target_group_arns = [aws_lb_target_group.frontendtg.arn]
  health_check_type = "ELB"

  vpc_zone_identifier = var.private_subnets
  max_size = var.max_size
  min_size = var.min_size

   tag {
    key                 = "Name"
    value               = "frontend-${var.env}"
    propagate_at_launch = true
  }
}

resource "aws_autoscaling_group" "backend-asg" {
  launch_template {
    id = aws_launch_template.backend-template.id
    version = "$Latest"
  }

  target_group_arns = [aws_lb_target_group.backendtg.arn]
  health_check_type = "ELB"

  vpc_zone_identifier = var.private_subnets
  max_size = var.max_size
  min_size = var.min_size

   tag {
    key                 = "Name"
    value               = "backend-${var.env}"
    propagate_at_launch = true
  }
}

# loadbalancer
###################################################################

resource "aws_lb" "lb-backend" {
  name               = "backend-lb-${var.env}"
  load_balancer_type = "application"
  subnets            = var.public_subnets
  security_groups    = [aws_security_group.sg-backend.id]
}

resource "aws_lb_listener" "lb-backend-listener" {
  load_balancer_arn = aws_lb.lb-backend.arn
  port              = local.backend_port
  protocol          = "HTTP"

  default_action {
    type = "fixed-response"

    fixed_response {
      content_type  = "text/plain"
      message_body  = "404: page not found"
      status_code   = 404
    }
  }
}

resource "aws_lb_target_group" "backendtg" {
  name     = "backend-${var.env}"
  port     = local.backend_port
  protocol = "HTTP"
  vpc_id   = var.vpc_id
  health_check {
    path = "/health"
    protocol = "HTTP"
    matcher = "200"
    interval = 15
    timeout = 3
    healthy_threshold = 2
    unhealthy_threshold = 2
  }
}

resource "aws_lb_listener_rule" "backendrule" {
  listener_arn  = aws_lb_listener.lb-backend-listener.arn
  priority      = 100

  condition {
    path_pattern {
      values = ["*"]
    }
  }

  action {
    type              = "forward"
    target_group_arn  = aws_lb_target_group.backendtg.arn
  }
}

resource "aws_lb" "lb-frontend" {
  name               = "frontend-lb-${var.env}"
  load_balancer_type = "application"
  subnets            = var.public_subnets
  security_groups    = [aws_security_group.sg-frontend.id]
}

resource "aws_lb_listener" "lb-frontend-listener" {
  load_balancer_arn = aws_lb.lb-frontend.arn
  port              = local.http_port
  protocol          = "HTTP"

  default_action {
    type = "fixed-response"

    fixed_response {
      content_type  = "text/plain"
      message_body  = "404: page not found"
      status_code   = 404
    }
  }
}

resource "aws_lb_target_group" "frontendtg" {
  name     = "frontend-${var.env}"
  port     = local.http_port
  protocol = "HTTP"
  vpc_id   = var.vpc_id

  health_check {
    path = "/"
    protocol = "HTTP"
    matcher = "200"
    interval = 15
    timeout = 3
    healthy_threshold = 2
    unhealthy_threshold = 2
  }
}

resource "aws_lb_listener_rule" "frontendrule" {
  listener_arn  = aws_lb_listener.lb-frontend-listener.arn
  priority      = 100

  condition {
    path_pattern {
      values = ["*"]
    }
  }

  action {
    type              = "forward"
    target_group_arn  = aws_lb_target_group.frontendtg.arn
  }
}
