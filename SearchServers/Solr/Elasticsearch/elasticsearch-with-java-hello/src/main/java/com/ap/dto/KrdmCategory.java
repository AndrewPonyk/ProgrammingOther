package com.ap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KrdmCategory {
    @JsonProperty("key")
    private String entityName;
    @JsonProperty("doc_count")
    private Long docsCount;
}
