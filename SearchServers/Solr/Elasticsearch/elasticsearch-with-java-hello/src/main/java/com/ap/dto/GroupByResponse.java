package com.ap.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupByResponse {

    ArrayNode aggregations;

    @JsonProperty("aggregations")
    public void setAggregations(JsonNode aggregations) {
        ArrayNode jsonNode = (ArrayNode) aggregations.get("krdm_categories").get("buckets");
        System.out.println(jsonNode);
        this.aggregations = jsonNode;
    }
}