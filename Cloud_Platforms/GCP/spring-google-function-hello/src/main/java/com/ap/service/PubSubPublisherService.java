package com.ap.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.stereotype.Service;

@Service
public class PubSubPublisherService{
    private static final Logger LOGGER = LoggerFactory.getLogger(PubSubPublisherService.class);

    private final PubSubTemplate pubSubTemplate;

    @Autowired
    public PubSubPublisherService(PubSubTemplate pubSubTemplate) {
        this.pubSubTemplate = pubSubTemplate;
    }

    public void publish(String message){
        LOGGER.info("Publishing to topic [{}]. Message: [{}]", "exampleTopic", message);
        pubSubTemplate.publish("exampleTopic", message);
    }
}
