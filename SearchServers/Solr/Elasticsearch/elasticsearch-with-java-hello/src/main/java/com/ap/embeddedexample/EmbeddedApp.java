package com.ap.embeddedexample;

import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.IndexSettings;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import java.io.IOException;

/**
 * Created by andrii on 28.12.17.
 */
public class EmbeddedApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("test embedded EL");
        final EmbeddedElastic embeddedElastic = EmbeddedElastic.builder()
                .withElasticVersion("5.0.0")
                .withSetting(PopularProperties.TRANSPORT_TCP_PORT, 9350)
                .withSetting(PopularProperties.CLUSTER_NAME, "my_cluster")
                .withSetting(PopularProperties.HTTP_PORT, 9200)
                //.withPlugin("analysis-stempel")
//                .withIndex("cars", IndexSettings.builder()
//                        .withType("car", getSystemResourceAsStream("car-mapping.json"))
//                        .build())
//                .withIndex("books", IndexSettings.builder()
//                        .withType(PAPER_BOOK_INDEX_TYPE, getSystemResourceAsStream("paper-book-mapping.json"))
//                        .withType("audio_book", getSystemResourceAsStream("audio-book-mapping.json"))
//                        .withSettings(getSystemResourceAsStream("elastic-settings.json"))
//                        .build())
                .build()
                .start();
    }
}
