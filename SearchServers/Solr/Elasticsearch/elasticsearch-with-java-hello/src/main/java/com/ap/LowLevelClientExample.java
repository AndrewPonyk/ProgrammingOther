package com.ap;

import com.ap.dto.GroupByResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.HashMap;

public class LowLevelClientExample {
    private static final String SEARCH = "{\"stored_fields\" : [\"entityName\"],\"aggs\": {\"krdm_categories\": {\"terms\": {\"field\": \"entityName\",\"size\" : 100}}}}";
    public static ObjectMapper objectMapper = new ObjectMapper();


    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = null;

            client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 9200, "http")));


        Header header = new BasicHeader("content-type", "application/json");
        Response response = client.getLowLevelClient().performRequest("POST", "/temp/_search", new HashMap<String, String>(),
                EntityBuilder.create().setText(SEARCH).build(), header);

        String responseText = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println(responseText);

        GroupByResponse groupByResponse = objectMapper.readValue(responseText, GroupByResponse.class);

        client.close();
    }
}