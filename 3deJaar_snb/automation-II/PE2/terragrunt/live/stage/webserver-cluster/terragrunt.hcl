include {
  path = find_in_parent_folders()
}

terraform {
  source = "../../../modules/webserver-cluster"
}

dependency "vpc" {
  config_path = "../vpc"
}

dependency "rds" {
  config_path = "../database"
}

dependency "s3" {
  config_path = "../s3"
}

inputs = {
  vpc_id = dependency.vpc.outputs.vpc_id
  public_subnets = dependency.vpc.outputs.public_subnets
  private_subnets = dependency.vpc.outputs.private_subnets
  sgDatabase = dependency.rds.outputs.sgDatabase
  branch = "sequelize"
  min_size = 1
  max_size = 2
  env = "stage"
  user_data_backend = <<-EOF
              #!/bin/bash
              yum update -y
              yum install -y git nodejs
              git clone https://github.com/DriesMelottePXL/CloudToDoApp.git
              cd CloudToDoApp/backend/
              git checkout sequelize
              sed -i "s/host: process.env.DBURL || 'localhost'/host: '${dependency.rds.outputs.db_url}'/" db.js
              sed -i "s/username: process.env.DBUSER || 'root'/username: '${dependency.rds.outputs.db_user}'/" db.js
              sed -i "s/password: process.env.DBPASSWORD || ''/password: '${dependency.rds.outputs.db_pass}'/" db.js
              sed -i "s/database: process.env.DBDATABASE || 'todo'/database: '${dependency.rds.outputs.db_name}'/" db.js
              cd routes
              sed -i 's/process\.env\.BUCKET/"${dependency.s3.outputs.bucket}"/g' carrousel.routes.js
              cd ..
              npm install aws-sdk
              npm install
              npm start
              EOF
}