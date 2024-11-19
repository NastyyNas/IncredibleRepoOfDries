variable "vpc_id" {
    type = string
}

variable "db_user" {
    type = string
}

variable "db_pass" {
    type = string
}

variable "db_name" {
    type = string
}

variable "private_subnets" {
    type = list(string)
}