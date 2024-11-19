include {
  path = find_in_parent_folders()
}

terraform {
  source = "../../../modules/webserver-cluster"
}

dependency "vpc" {
  config_path = "../vpc"
}

dependency "dynamodb" {
  config_path = "../database"
}

dependency "s3" {
  config_path = "../s3"
}

inputs = {
  vpc_id = dependency.vpc.outputs.vpc_id
  public_subnets = dependency.vpc.outputs.public_subnets
  private_subnets = dependency.vpc.outputs.private_subnets
  branch = "dynamo-db"
  min_size = 1
  max_size = 4
  env = "prod"
  user_data_backend = <<-EOF
              #!/bin/bash
              yum update -y
              yum install -y git nodejs
              git clone https://github.com/DriesMelottePXL/CloudToDoApp.git
              cd CloudToDoApp/backend/
              git checkout dynamo-db
              echo "export AWS_TABLE_NAME=${dependency.dynamodb.outputs.table_name}" >> /etc/environment
              echo "export AWS_REGION=us-east-1" >> /etc/environment
              source /etc/environment
              cd routes
              sed -i 's/process\.env\.BUCKET/"${dependency.s3.outputs.bucket}"/g' carrousel.routes.js
              cd ..
              npm install aws-sdk
              npm install
              npm start
              EOF
}