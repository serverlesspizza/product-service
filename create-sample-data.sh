#!/bin/bash

aws dynamodb batch-write-item --request-items file://sample-data-dev.json --region eu-west-1
aws dynamodb batch-write-item --request-items file://sample-data-prod.json --region eu-west-1
