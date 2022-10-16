resource "aws_iam_policy" "policy" {
  name        = "space-codesoom-s3-upload-ci-policy"
  description = "space.codesoom.com S3 upload CI policy"

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        "Sid"    = "VisualEditor0",
        "Effect" = "Allow",
        "Action" = [
          "s3:PutObject",
          "s3:PutObjectAcl"
        ],
        "Resource" = [
          aws_s3_bucket.bucket.arn,
          "${aws_s3_bucket.bucket.arn}/*",
        ]
      },
    ]
  })
}
