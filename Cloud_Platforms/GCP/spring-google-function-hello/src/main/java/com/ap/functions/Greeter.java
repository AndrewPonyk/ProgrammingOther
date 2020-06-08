package com.ap.functions;

import com.ap.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Greeter implements Function<String, String> {

    @Autowired
    private ValidationService validationService;

    @Override
    public String apply(String s) {

        return  validationService.validate(s) + "Hello " + s + ", and welcome to Spring Cloud Function!!!";
    }
}