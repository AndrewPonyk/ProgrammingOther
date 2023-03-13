package com.ap.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.*;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.client.util.Sleeper;
import com.google.api.services.pubsub.Pubsub;
import com.google.api.services.pubsub.model.PublishRequest;
import com.google.api.services.pubsub.model.PubsubMessage;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import static java.util.Objects.isNull;

public class PubSubPublisher  {
    private static final Logger logger = LoggerFactory.getLogger(PubSubPublisher.class);
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static Pubsub pubsub = null;
    private PubSubPublisher (){
    }
    public static class RetryHttpInitializerWrapper implements HttpRequestInitializer {
        private final GoogleCredential wrappedCredential;
        private final Sleeper sleeper;
        private RetryHttpInitializerWrapper(GoogleCredential wrappedCredential) {
            this(wrappedCredential, Sleeper.DEFAULT);
        }
        RetryHttpInitializerWrapper(
                GoogleCredential wrappedCredential, Sleeper sleeper) {
            this.wrappedCredential = Preconditions.checkNotNull(wrappedCredential);
            this.sleeper = sleeper;
        }
        @Override
        public void initialize(HttpRequest request) {
            final HttpUnsuccessfulResponseHandler backoffHandler =
                    new HttpBackOffUnsuccessfulResponseHandler(
                            new ExponentialBackOff())
                            .setSleeper(sleeper);
            request.setInterceptor(wrappedCredential);
            request.setUnsuccessfulResponseHandler(
                    new HttpUnsuccessfulResponseHandler() {
                        @Override
                        public boolean handleResponse(HttpRequest request,
                                                      HttpResponse response,
                                                      boolean supportsRetry)
                                throws IOException {
                            if (wrappedCredential.handleResponse(request,
                                    response,
                                    supportsRetry)) {
                                return true;
                            } else if (backoffHandler.handleResponse(request,
                                    response,
                                    supportsRetry)) {
                                logger.info("Retrying {}", request.getUrl());
                                return true;
                            } else {
                                return false;
                            }
                        }
                    });
            request.setIOExceptionHandler(new HttpBackOffIOExceptionHandler(
                    new ExponentialBackOff()).setSleeper(sleeper));
        }
    }
    public static Pubsub createPubsubClient() {
        try {
            HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
            GoogleCredential credential = GoogleCredential.getApplicationDefault();
            HttpRequestInitializer initializer =
                    new RetryHttpInitializerWrapper(credential);
            return new Pubsub.Builder(transport, JSON_FACTORY, initializer).setApplicationName("myapp").build();
        } catch (IOException | GeneralSecurityException e) {
            logger.error("Could not create Pubsub client: {}", e);
        }
        return null;
    }
    private static Pubsub getInstance() {
        if (isNull(pubsub)) {
            pubsub = createPubsubClient();
            return pubsub;
        }
        return pubsub;
    }
    public static boolean publishMessage(byte[] data, String outputTopic) {
        // Publish message to Pubsub.
        PubsubMessage pubsubMessage = new PubsubMessage();
        pubsubMessage.encodeData(data);
        PublishRequest publishRequest = new PublishRequest();
        publishRequest.setMessages(Collections.singletonList(pubsubMessage));
        try {
            getInstance().projects().topics().publish(outputTopic, publishRequest).execute();
            return true;
        } catch (Exception e) {
            logger.error("Pubsub Error: {}", e);
            return false;
        }
    }
}