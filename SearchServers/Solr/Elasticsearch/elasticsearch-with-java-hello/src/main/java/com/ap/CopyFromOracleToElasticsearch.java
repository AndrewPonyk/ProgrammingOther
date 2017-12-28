package com.ap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyFromOracleToElasticsearch {
    static RestHighLevelClient client =             client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("localhost", 9200, "http")));;

    public static void main(String[] args) throws SQLException, IOException {

        Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:49161:xe","system","oracle");
        ResultSet krdmDataSet = conn.createStatement().executeQuery("select * from KRDM_REFERENCE_DATA");
        int counter = 1;
        ObjectMapper objectMapper = new ObjectMapper();
        while (krdmDataSet.next()){
            String entity_name = krdmDataSet.getString("ENTITY_NAME");
            String entity_key = krdmDataSet.getString("ENTITY_KEY");
            String data = krdmDataSet.getString("DATA");
            String language_descrptions = krdmDataSet.getString("LANGUAGE_DESCRIPTIONS");
            KrdmDto krdmDto = new KrdmDto();
            krdmDto.setEntityName(entity_name);
            krdmDto.setEntityKey(entity_key);
            krdmDto.setData(objectMapper.readValue(data, new TypeReference<Map<String, String>>(){}));
            krdmDto.setLanguageDescriptions(objectMapper.readValue(language_descrptions, new TypeReference<List<LanguageDescriptionDto>>(){}));

            ////////////
            //postDataToEl(objectMapper.writeValueAsString(krdmDto));
            System.out.println(objectMapper.writeValueAsString(krdmDto));
            System.out.println(counter++);
        }

        conn.close();
        client.close();
    }

    public static void postDataToEl(String document) throws IOException {
        try {
            IndexRequest indexRequest = new IndexRequest("krdm", "doc")
                    .source(document, XContentType.JSON);
            IndexResponse indexResponse = client.index(indexRequest);

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
