include {
  path = find_in_parent_folders()
}

terraform {
  source = "../../../modules/rds"
}

dependency "vpc" {
  config_path = "../vpc"
}

inputs = {
  db_name = "todo"
  db_user = "admin"
  db_pass = "TomIsCool"
  private_subnets = dependency.vpc.outputs.private_subnets
  vpc_id = dependency.vpc.outputs.vpc_id
}