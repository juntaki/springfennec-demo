#!/bin/bash -x

: Run ./gradlew clean bootRun

: TODO: currently curl will fail due to spring security
curl "http://localhost:8080/v2/api-docs?group=springfox" > spec_springfox.json
curl "http://localhost:8080/v2/api-docs?group=springfox_suffix_test" > spec_springfox_suffix_test.json

cat spec_admin.json | jq . > spec_admin_pretty.json
cat spec_user.json | jq . > spec_user_pretty.json
cat spec_springfennec.json | jq . > spec_springfennec_pretty.json
cat spec_springfox.json | jq . > spec_springfox_pretty.json
cat spec_springfox_suffix_test.json | jq . > spec_springfox_suffix_test_pretty.json

: Swagger Codegen
swagger-codegen generate -l ruby -i spec_springfennec.json -o springfennec_gem

cd springfennec_gem
gem build swagger_client.gemspec

: Uncomment if you want to try.
: gem i ./swagger_client-1.0.0.gem

: cd ../
: ruby client.rb
