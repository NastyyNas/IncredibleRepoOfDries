variable vpc_id {
  type        = string
}

variable public_subnets {
  type        = list(string)
}

variable private_subnets {
  type        = list(string)
}

variable sgDatabase {
  type        = string
  default = ""
}

variable "user_data_backend" {
  type = string
}

variable branch {
  type        = string
}

variable min_size {
  type        = number
}

variable max_size {
  type        = number
}

variable env {
  type        = string
}


