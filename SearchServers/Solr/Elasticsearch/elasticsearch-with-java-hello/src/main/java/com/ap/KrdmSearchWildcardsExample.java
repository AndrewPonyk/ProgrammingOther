package com.ap;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.HashMap;

public class KrdmSearchWildcardsExample {



    public static void main(String[] args) throws IOException {
        System.out.println("Elaticsearch integration with java");
        RestHighLevelClient client = null;

        try {
            client = new RestHighLevelClient(
                    RestClient.builder(
                    new HttpHost("localhost", 9200, "http")));
            SearchRequest searchRequest = new SearchRequest("temp");
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

            //QueryStringQueryBuilder queryBuilder = QueryBuilders.queryStringQuery("10*0 t*re* tw*").defaultOperator(Operator.AND);
            //MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("t*re* tw* 10*0", "name" );
            //simple wildcard query
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            //queryBuilder.must(QueryBuilders.queryStringQuery("t*re* tw* 100").defaultOperator(Operator.AND));

            //simple wildcard query
            //queryBuilder.must(QueryBuilders.queryStringQuery("t*re* tw* 100").defaultOperator(Operator.AND));

            // AND operator query
            //queryBuilder.must(QueryBuilders.matchQuery("name", "one two").operator(Operator.OR));

            // minimumShouldMatch query
            //queryBuilder.must(QueryBuilders.matchQuery("name", "one two three 130").minimumShouldMatch("75%"));


            sourceBuilder.size(50);
            sourceBuilder.query(queryBuilder);
            searchRequest.source(sourceBuilder);

            SearchResponse search = client.search(searchRequest);


            for (SearchHit item : search.getHits()) {
                System.out.println(item.getSourceAsString());
                System.out.println("========");
            }
        } finally {
            client.close();
        }
    }
}