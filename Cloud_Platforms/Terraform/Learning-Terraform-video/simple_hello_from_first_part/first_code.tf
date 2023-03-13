provider "aws"{
	profile = "default"
	region = "us-east-1"
}

resource "aws_s3_bucket" "b" {
	bucket = "sfsdfasfoi34-23"
}