Test read me

Deploying to google cloud:
gcloud  functions deploy function-sample-gcp-http 
--entry-point org.springframework.cloud.function.adapter.gcloud.FunctionInvoker 
--runtime java11 --trigger-http --source deploy --memory 512MB