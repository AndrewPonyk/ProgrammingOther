package com.example.elasticsearchspringdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@SpringBootApplication
public class ElasticsearchSpringDataApplication implements CommandLineRunner {

	@Autowired
	private ElasticsearchOperations es;

	//@Autowired
	//private PeopleService peopleService;

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchSpringDataApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("COMMAND LINE RUNNER");
	}
}
