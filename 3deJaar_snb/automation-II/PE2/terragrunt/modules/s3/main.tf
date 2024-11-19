resource "aws_s3_bucket" "s3" {
  bucket = var.bucket

  tags = {
    Name        = var.bucket
  }
}

resource "aws_s3_object" "images" {
    bucket = aws_s3_bucket.s3.id
    for_each = fileset("images/", "**/*.jpg")

    key = each.value
    source = "images/${each.value}"
    content_type = each.value
}