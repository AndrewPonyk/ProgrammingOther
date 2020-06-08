package com.ap.functions;

import com.ap.service.PubSubPublisherService;
import com.ap.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SupFunction implements Function<String, String> {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private PubSubPublisherService pubSubPublisherService;

    @Override
    public String apply(String s) {
        pubSubPublisherService.publish(s);
        return  validationService.validate(s) + "Hello " + s + ", and welcome to Spring Cloud Function!!!";
    }
}