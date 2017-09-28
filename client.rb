require 'swagger_client'

api_instance = SwaggerClient::DefaultApi.new

param = SwaggerClient::ComJuntakiSpringfennecdemoApplicationUserApiParam.new

begin
  result = api_instance.create_user(param)
  p result
rescue SwaggerClient::ApiError => e
  puts "Exception when calling DefaultApi->create_user: #{e}"
end

begin
  result = api_instance.get_user(1)
  p result
rescue SwaggerClient::ApiError => e
  puts "Exception when calling DefaultApi->get_user: #{e}"
end

