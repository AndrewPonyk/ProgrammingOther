package com.ap;

import org.apache.http.HttpHost;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
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
                            new HttpHost("localhost", 9900, "http"),
                            new HttpHost("localhost", 9800, "http"),
                    new HttpHost("localhost", 9200, "http")));
            SearchRequest searchRequest = new SearchRequest("kvm1");
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

            //simple query
            queryBuilder.must(QueryBuilders.wildcardQuery("vendorCode", "* 101*"));
            //queryBuilder.must(QueryBuilders.wildcardQuery("vcClassCode", "*1"));

            // nested wildcard query
            //queryBuilder.must(QueryBuilders.nestedQuery("data", QueryBuilders.wildcardQuery("data.Desc", "*jun*"), ScoreMode.Avg));


            //sourceBuilder.from(0);
            //sourceBuilder.size(5);
            //sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
            sourceBuilder.size(50);
            sourceBuilder.query(queryBuilder);
            searchRequest.source(sourceBuilder);



            SearchResponse search = client.search(searchRequest);

            for (SearchHit item : search.getHits()) {
                System.out.println(item.getSourceAsString());
                System.out.println("========");
            }

            List<KrdmDto> results = null;
            List<SearchHit> collect = StreamSupport.stream(search.getHits().spliterator(), false).map(item -> item).collect(Collectors.toList());
        } finally {
            client.close();
        }
    }
}