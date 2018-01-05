package com.ap.embeddedexample;

import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.IndexSettings;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.ClassLoader.getSystemResourceAsStream;

/**
 * Created by andrii on 28.12.17.
 */
public class EmbeddedApp {

    public static void main(String[] args) {
        try {
            new EmbeddedApp().test();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void test() throws IOException, InterruptedException {
        System.out.println("test embedded EL");
        final EmbeddedElastic embeddedElastic = EmbeddedElastic.builder()
                .withElasticVersion("6.1.1")
                .withSetting(PopularProperties.TRANSPORT_TCP_PORT, 9351)
                .withSetting(PopularProperties.CLUSTER_NAME, "my_cluster")
                .withSetting(PopularProperties.HTTP_PORT, 9200)
                .withStartTimeout(100, TimeUnit.SECONDS)
                //.withPlugin("analysis-stempel")
                .withIndex("krdm", IndexSettings.builder()
                        .withType("doc", getSystemResourceAsStream("krdm.json"))
                        .build())
//                .withIndex("books", IndexSettings.builder()
//                        .withType(PAPER_BOOK_INDEX_TYPE, getSystemResourceAsStream("paper-book-mapping.json"))
//                        .withType("audio_book", getSystemResourceAsStream("audio-book-mapping.json"))
//                        .withSettings(getSystemResourceAsStream("elastic-settings.json"))
//                        .build())
                .build()
                .start();


        //Thread.sleep(10000);

        //System.out.println("killing embedded elasticsearch");
        //embeddedElastic.stop();
    }


    private String getFile(String fileName) {

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = EmbeddedApp.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }
}
