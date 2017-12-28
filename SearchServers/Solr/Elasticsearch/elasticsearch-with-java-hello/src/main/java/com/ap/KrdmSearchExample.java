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
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class KrdmSearchExample {
    public static void main(String[] args) throws IOException {
        System.out.println("Elaticsearch integration with java");
        RestHighLevelClient client = null;

        try {
            client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 9200, "http")));
            SearchRequest searchRequest = new SearchRequest("krdm1");
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

//            sourceBuilder.query(QueryBuilders.boolQuery()
//                    .must(QueryBuilders.matchQuery("entityName", "MDT_AGE_GRP"))
////                    .must(QueryBuilders.matchQuery("entityKey", "Veyzzbx"))
//            );
            //simple query
            //sourceBuilder.query(QueryBuilders.wildcardQuery("entityKey", "key*"));

            // nested wildcard query
            sourceBuilder.query(QueryBuilders.nestedQuery("data", QueryBuilders.wildcardQuery("data.Desc", "momoasmuyavtcu"), ScoreMode.Avg));


            //sourceBuilder.from(0);
            //sourceBuilder.size(5);
            //sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
            sourceBuilder.size(5);
            searchRequest.source(sourceBuilder);

            SearchResponse search = client.search(searchRequest);

            for (SearchHit item : search.getHits()) {
                System.out.println(item.getSourceAsString());
            }

            List<KrdmDto> results = null;
            List<SearchHit> collect = StreamSupport.stream(search.getHits().spliterator(), false).map(item -> item).collect(Collectors.toList());
        } finally {
            client.close();
        }
    }
}

