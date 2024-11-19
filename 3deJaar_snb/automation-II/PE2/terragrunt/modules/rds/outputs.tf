output "db_url" {
  value = aws_db_instance.default.address
}

output "db_user" {
  value = aws_db_instance.default.username
}

output "db_pass" {
  value = aws_db_instance.default.password
  sensitive = true
}

output "db_name"{
  value = aws_db_instance.default.db_name
}

output sgDatabase {
  value       = aws_security_group.sg-database.id
}
