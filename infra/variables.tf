locals {
  acm = "arn:aws:acm:us-east-1:466782999783:certificate/be52637a-b8da-43e5-bce8-99358955b673"
  admin-acm = "arn:aws:acm:us-east-1:466782999783:certificate/87a5bd62-d603-49c6-a61f-2c29f867b98a"

  tags = {
    env  = "production",
    Name = "codesoom"
  }
}
