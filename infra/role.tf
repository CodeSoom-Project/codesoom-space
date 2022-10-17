resource "aws_iam_role" "upload-role" {
  name = "space-codesoom-web-upload"

  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Effect = "Allow",
        Principal = {
          Federated = "arn:aws:iam::466782999783:oidc-provider/token.actions.githubusercontent.com"
        },
        Action = "sts:AssumeRoleWithWebIdentity",
        Condition = {
          StringLike = {
            "token.actions.githubusercontent.com:sub" = "repo:CodeSoom-Project/my-seat:ref:refs/heads/main"
          },
          "ForAllValues:StringEquals" = {
            "token.actions.githubusercontent.com:iss" = "https://token.actions.githubusercontent.com",
            "token.actions.githubusercontent.com:aud" = "sts.amazonaws.com"
          }
        }
      }
    ]
  })

  managed_policy_arns = [
    aws_iam_policy.policy.arn
  ]
}
