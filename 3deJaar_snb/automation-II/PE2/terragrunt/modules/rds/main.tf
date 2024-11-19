resource "aws_security_group" "sg-database" {
  name = "database-sg"
  vpc_id = var.vpc_id

  ingress {
    description = "Allow mysql"
    from_port = 3306
    to_port = 3306
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
    Name = "database-sg"
  }
}

resource "aws_db_instance" "default" {
  engine               = "mysql"
  engine_version       = "8.0.39"
  allocated_storage    = 20
  storage_type         = "gp2"
  identifier           = var.db_name
  username             = var.db_user
  password             = var.db_pass
  publicly_accessible  = true
  db_name              = var.db_name
  instance_class       = "db.t4g.micro"
  parameter_group_name = "default.mysql8.0"
  option_group_name    = "default:mysql-8-0"
  vpc_security_group_ids = [aws_security_group.sg-database.id]
  db_subnet_group_name = aws_db_subnet_group.subnet_group.name
  skip_final_snapshot  = true
}

resource "aws_db_subnet_group" "subnet_group" {
  name       = "main"
  subnet_ids = var.private_subnets

  tags = {
    Name = "My DB subnet group"
  }
}
