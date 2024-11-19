resource "aws_vpc" "app_vpc" {
  cidr_block = "172.32.1.0/24"
  enable_dns_hostnames = true 
  enable_dns_support   = true

  tags = {
    Name = "${var.vpc_name} VPC"
  }
}

resource "aws_subnet" "public_1" {
  vpc_id                  = aws_vpc.app_vpc.id
  cidr_block              = "172.32.1.0/26"
  availability_zone       = "us-east-1a"
  map_public_ip_on_launch = true
  tags = {
    Name = "public subnet 1"
  }
}

resource "aws_subnet" "public_2" {
  vpc_id                  = aws_vpc.app_vpc.id
  cidr_block              = "172.32.1.64/26"
  availability_zone       = "us-east-1b"
  map_public_ip_on_launch = true
  tags = {
    Name = "public subnet 2"
  }
}

resource "aws_subnet" "private_1" {
  vpc_id            = aws_vpc.app_vpc.id
  cidr_block        = "172.32.1.128/26"
  availability_zone = "us-east-1a"
  tags = {
    Name = "private subnet 2"
  }
}

resource "aws_subnet" "private_2" {
  vpc_id            = aws_vpc.app_vpc.id
  cidr_block        = "172.32.1.192/26"
  availability_zone = "us-east-1b"
  tags = {
    Name = "private subnet 2"
  }
}

resource "aws_internet_gateway" "igw" {
  vpc_id = aws_vpc.app_vpc.id
}

resource "aws_eip" "nat_eip" {
  domain = "vpc"
}

resource "aws_nat_gateway" "nat_gw" {
  allocation_id = aws_eip.nat_eip.id
  subnet_id     = aws_subnet.public_1.id
}

resource "aws_route_table" "public_rt" {
  vpc_id = aws_vpc.app_vpc.id
}

resource "aws_route" "public_route" {
  route_table_id         = aws_route_table.public_rt.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.igw.id
}

resource "aws_route_table_association" "public_assoc_1" {
  subnet_id      = aws_subnet.public_1.id
  route_table_id = aws_route_table.public_rt.id
}

resource "aws_route_table_association" "public_assoc_2" {
  subnet_id      = aws_subnet.public_2.id
  route_table_id = aws_route_table.public_rt.id
}

resource "aws_route_table" "private_rt" {
  vpc_id = aws_vpc.app_vpc.id
}

resource "aws_route" "private_route" {
  route_table_id         = aws_route_table.private_rt.id
  destination_cidr_block = "0.0.0.0/0"
  nat_gateway_id         = aws_nat_gateway.nat_gw.id
}

resource "aws_route_table_association" "private_assoc_1" {
  subnet_id      = aws_subnet.private_1.id
  route_table_id = aws_route_table.private_rt.id
}

resource "aws_route_table_association" "private_assoc_2" {
  subnet_id      = aws_subnet.private_2.id
  route_table_id = aws_route_table.private_rt.id
}