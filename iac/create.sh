#!/bin/bash
aws cloudformation create-stack --stack-name 'serverlesspizza-product-service-pipeline' \
	--template-body file://cfn_codepipeline.yml --region eu-west-1 \
  --parameters ParameterKey=GitHubToken,ParameterValue=$AWS_GITHUB_TOKEN \
	--capabilities CAPABILITY_NAMED_IAM CAPABILITY_AUTO_EXPAND
