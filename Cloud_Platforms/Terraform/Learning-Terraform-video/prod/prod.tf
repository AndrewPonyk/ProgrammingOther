provider "aws" {
  profile = "default"
  region = "us-west-2"
}

resource "aws_s3_bucket"  "prod_tf_course" {
  bucket = "tf-course-2022300333"
}

resource "aws_default_vpc" "default"{
}

resource "aws_security_group" "prod_web"{
  name = "prod-web"
  description = "Allow http/https ports inbound and all outbound"
  
  ingress {
    from_port = 80
	to_port = 80
	protocol = "tcp"
	cidr_blocks = ["0.0.0.0/0"]
  }
  ingress {
    from_port = 443
	to_port = 443
	protocol = "tcp"
	cidr_blocks = ["0.0.0.0/0"]
  }
  
  egress  {
    from_port = 0 #MEANS ALL PORTS
	to_port = 0
	protocol = "-1" # MEANS NO RESTRICTIONS
	cidr_blocks = ["0.0.0.0/0"]
  }
  
  tags = {
    "Terraform" : "true"
  }
}

resource "aws_instance" prod_web{
  ami = "ami-048cf64098d1f7264"
  instance_type = "t2.nano"
  vpc_security_group_ids=[aws_security_group.prod_web.id]
  tags = {
    "Terraform" : "true"
  }
}