package com.ap.config;

import com.ap.service.PubSubPublisher;
import com.google.api.services.pubsub.Pubsub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PubSubPublisherConfig {
    @Bean
    public Pubsub pubSubPublisher(){
        return PubSubPublisher.createPubsubClient();
    }
}
