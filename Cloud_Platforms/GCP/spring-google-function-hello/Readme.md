Test read me

Deploying to google cloud:
gcloud  functions deploy function-sample-gcp-http --entry-point org.springframework.cloud.function.adapter.gcp.GcfJarLauncher y--runtime java11 --trigger-http --source target/deploy --memory 512MB