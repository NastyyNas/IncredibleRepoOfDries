# AWS Workshop

## Choose region
```bash
provider "aws" {
  region = "us-west-2"
}
```


## Create a security group
```bash
resource "aws_security_group" "instance" {
  name = "terraform-example-instance"
  ingress {
    from_port = var.server_port
    to_port = var.server_port
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
```

## Create an instance with a shell script

**Je kan de security group ID aanroepen met de [resource naam].instance.id**

```bash
resource "aws_instance" "example" {
  ami = "ami-07eeacb3005b9beae"
  instance_type = "t2.micro"
  vpc_security_group_ids = [aws_security_group.instance.id]

  user_data = base64encode(<<-EOF
    #!/bin/bash
    echo "Hello, World" > /home/ubuntu/index.html
    nohup busybox httpd -f -p 8080 -h /home/ubuntu &
    EOF
  )

  tags = {
    Name = "terraform-example"
  }
}
```

## Input variable

### number example
```bash
variable "number_example" {
  description = "An example of a number variable in Terraform"
  type = number
  default = 42
}
```

### list example
```bash
variable "list_example" {
  description = "An example of a list in Terraform"
  type = list
  default = ["a", "b", "c"]
}
```

### combo
```bash
variable "list_numeric_example" {
  description = "An example of a numeric list in Terraform"
  type = list(number)
  default = [1, 2, 3]
}
```

### map
```bash
variable "map_example" {
  description = "An example of a map in Terraform"
  type = map(string)
  default = {
    key1 = "value1"
    key2 = "value2"
    key3 = "value3"
  }
}
```

### object
```bash
variable "object_example" {
  description = "An example of a structural type in Terraform"
  type = object({
    name = string
    age = number
    tags = list(string)
    enabled = bool
  })
  default = {
    name = "value1"
    age = 42
    tags = ["a", "b", "c"]
    enabled = true
  }
}
```

### our webserver variable
```bash
variable "server_port" {
  description = "The port the server will use for HTTP requests"
  type = number
  default = 8080
}
```

## use input variables in code
```bash
user_data = base64encode(<<-EOF
    #!/bin/bash
    echo "Hello, World" > /home/ubuntu/index.html
    nohup busybox httpd -f -p ${var.server_port} -h /home/ubuntu &
    EOF
  )
```

## output variable
```bash
output "public_ip" {
  value = aws_instance.example.public_ip
  description = "The public IP address of the web server"
}
```

## Create a launch template
```bash
resource "aws_launch_template" "example" {
  name = "example-launch-template"

  image_id      = "ami-07eeacb3005b9beae"
  instance_type = "t2.micro"

  iam_instance_profile {
    name = "LabInstanceProfile"
  }

  user_data = base64encode(<<-EOF
    #!/bin/bash
    echo "Hello, World" > /home/ubuntu/index.html
    nohup busybox httpd -f -p ${var.server_port} -h /home/ubuntu &
    EOF
  )

  vpc_security_group_ids = [aws_security_group.instance.id]
}
```

## Create an Auto Scaling Group (ASG)
```bash
resource "aws_autoscaling_group" "example" {
  launch_template {
    id      = aws_launch_template.example.id
    version = "$Latest"
  }

  health_check_type = "ELB"

  min_size = 2
  max_size = 10

  tag {
    key                 = "Name"
    value               = "terraform-asg-example"
    propagate_at_launch = true
  }
}
```

## data sources

### vpc data source
```bash
data "aws_vpc" "default" {
  default = true
}
```
**Met de aws_vpc data source is de enige filter die je nodig hebt default = true, waardoor Terraform de Default VPC in je AWSaccount opzoekt.**

### subnet data source
```bash
data "aws_subnets" "default" {
  filter {
    name = "vpc-id"
    values = [data.aws_vpc.default.id]
  }
}
```

**Hier zoekt die de default subnet voor de data source vpc die we gemaakt hebben**

## add availability zones to the ASG
```bash
resource "aws_autoscaling_group" "example" {
  launch_template {
    id      = aws_launch_template.example.id
    version = "$Latest"
  }

  health_check_type = "ELB"

  # vpc_zone_identifier  = data.aws_subnets.default.ids # let AWS decide which subnets to use
  availability_zones = ["us-west-2a", "us-west-2b", "us-west-2c"] # specify valid AZs

  min_size = 2
  max_size = 10

  tag {
    key                 = "Name"
    value               = "terraform-asg-example"
    propagate_at_launch = true
  }
}
```

## Create an application loadbalancer
```bash
resource "aws_lb" "example" {
  name = "terraform-asg-example"
  load_balancer_type = "application"
  subnets = data.aws_subnets.default.ids
}
```

## Create a loadbalancer listener
```bash
resource "aws_lb_listener" "http" {
  load_balancer_arn = aws_lb.example.arn
  port = 80
  protocol = "HTTP"

  # By default, return a simple 404 page
  default_action {
    type = "fixed-response"

    fixed_response {
      content_type = "text/plain"
      message_body = "404: page not found"
      status_code = 404
    }
  }
}
```

## Create an application loadbalancer security group
```bash
resource "aws_security_group" "alb" {
  name = "terraform-example-alb"

  # Allow inbound HTTP requests
  ingress {
    from_port = 80
    to_port = 80
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  # Allow all outbound requests
  egress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
```

### add the security group argument in the loadbalancer code
```bash
resource "aws_lb" "example" {
  name = "terraform-asg-example"
  load_balancer_type = "application"
  subnets = data.aws_subnets.default.ids
  security_groups = [aws_security_group.alb.id]
}
```

## Create target group
```bash
resource "aws_lb_target_group" "asg" {
  name = "terraform-asg-example"
  port = var.server_port
  protocol = "HTTP"
  vpc_id = data.aws_vpc.default.id

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
```

### add the target group to the autoscaling group
```bash
resource "aws_autoscaling_group" "example" {
  launch_template {
    id      = aws_launch_template.example.id
    version = "$Latest"
  }

  target_group_arns = [aws_lb_target_group.asg.arn]
  health_check_type = "ELB"

  # vpc_zone_identifier  = data.aws_subnets.default.ids # let AWS decide which subnets to use
  availability_zones = ["us-west-2a", "us-west-2b", "us-west-2c"] # specify valid AZs

  min_size = 2
  max_size = 10

  tag {
    key                 = "Name"
    value               = "terraform-asg-example"
    propagate_at_launch = true
  }
}
```

## Create a listener rule
```bash
resource "aws_lb_listener_rule" "asg" {
  listener_arn = aws_lb_listener.http.arn
  priority = 100
  condition {
    path_pattern {
      values = ["*"]
    }
  }
  action {
    type = "forward"
    target_group_arn = aws_lb_target_group.asg.arn
  }
}
```

## Create an output variable to show the domain name of the lb
```bash
output "alb_dns_name" {
  value = aws_lb.example.dns_name
  description = "The domain name of the load balancer"
}
```
