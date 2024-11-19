locals {
  parsed = regex("./live/(?P<env>.+)/.*", get_terragrunt_dir())
  env    = local.parsed.env
}

generate "provider" {
  path = "provider.tf"
  if_exists = "overwrite_terragrunt"
  contents = <<EOF
    provider "aws" {
    region = "us-east-1"
    }
EOF
}

remote_state {
  backend = "s3"
  config = {
    bucket = "11904402-state-bucket-${local.env}"
    region = "us-east-1"
    key    = "${path_relative_to_include()}/terraform.tfstate"
  }
  generate = {
    path      = "backend.tf"
    if_exists = "overwrite_terragrunt"
  }
}