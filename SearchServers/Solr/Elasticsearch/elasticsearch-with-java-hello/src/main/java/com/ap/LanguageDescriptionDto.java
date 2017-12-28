package com.ap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LanguageDescriptionDto {

    @JsonProperty("languageCode")
    private String languageCode;

    @JsonProperty("descriptions")
    private Map<String, String> descriptions;

}
