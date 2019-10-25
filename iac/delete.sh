#!/bin/bash

aws cloudformation delete-stack 
    --stack-name 'serverlesspizza-product-service-pipeline' 
    --region eu-west-1
    --profile aws-serverlesspizza-devops
