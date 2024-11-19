resource "aws_dynamodb_table" "todo_table" {
  name           = var.table
  billing_mode   = "PAY_PER_REQUEST"
  hash_key       = "_id"
  
  attribute {
    name = "_id"
    type = "S"
  }

  tags = {
    Environment = "production"
    Project     = "todo-app"
  }

  point_in_time_recovery {
    enabled = true
  }

  server_side_encryption {
    enabled = true
  }
}