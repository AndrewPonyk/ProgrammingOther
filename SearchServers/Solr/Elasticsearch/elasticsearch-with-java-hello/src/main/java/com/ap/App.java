package com.ap;

import org.apache.http.HttpHost;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Elaticsearch integration with java" );
        RestHighLevelClient client = null;

        try {


        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        SearchRequest searchRequest = new SearchRequest("people");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //simple query
        sourceBuilder.query(QueryBuilders.wildcardQuery("name", "an*ii"));

        // nested wildcard query
        sourceBuilder.query(QueryBuilders.nestedQuery("data", QueryBuilders.wildcardQuery("data.f","ab*"), ScoreMode.Avg));


        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);

        SearchResponse search = client.search(searchRequest);

        for (SearchHit item : search.getHits()) {
            System.out.println(item.getSourceAsString());
        }

        } finally {
            client.close();
        }
    }
}