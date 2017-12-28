package com.ap;


import java.util.List;
import java.util.Map;

public class KrdmDto {
    private String entityName;
    private String entityKey;
    private Map<String, String> data;
    private List<LanguageDescriptionDto> languageDescriptions;

    public List<LanguageDescriptionDto> getLanguageDescriptions() {
        return languageDescriptions;
    }

    public void setLanguageDescriptions(List<LanguageDescriptionDto> languageDescriptions) {
        this.languageDescriptions = languageDescriptions;
    }



    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityKey() {
        return entityKey;
    }

    public void setEntityKey(String entityKey) {
        this.entityKey = entityKey;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }


}
