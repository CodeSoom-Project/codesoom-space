locals {
  acm = "arn:aws:acm:us-east-1:466782999783:certificate/be52637a-b8da-43e5-bce8-99358955b673"

  tags = {
    env  = "production",
    Name = "codesoom"
  }
}
