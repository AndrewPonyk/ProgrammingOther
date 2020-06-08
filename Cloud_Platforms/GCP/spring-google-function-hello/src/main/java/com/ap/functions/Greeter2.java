package com.ap.functions;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Greeter2 implements Function<String, String> {
    @Override
    public String apply(String s) {
        return "Hello222 " + s + ", and welcome to Spring Cloud Function!!!";
    }
}