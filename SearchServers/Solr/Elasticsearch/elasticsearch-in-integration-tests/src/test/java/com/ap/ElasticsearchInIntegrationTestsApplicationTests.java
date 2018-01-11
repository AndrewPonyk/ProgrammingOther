package com.ap;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.Wait;
import org.testcontainers.shaded.org.apache.http.HttpResponse;
import org.testcontainers.shaded.org.apache.http.client.HttpClient;
import org.testcontainers.shaded.org.apache.http.client.methods.HttpPut;
import org.testcontainers.shaded.org.apache.http.entity.ContentType;
import org.testcontainers.shaded.org.apache.http.entity.StringEntity;
import org.testcontainers.shaded.org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchInIntegrationTestsApplicationTests {

	@ClassRule
	public static GenericContainer elasticContainer =
			new FixedHostPortGenericContainer("docker.elastic.co/elasticsearch/elasticsearch:5.3.0")
					.withFixedExposedPort(9200, 9200)
					.withFixedExposedPort(9300, 9300)
					.waitingFor(Wait.forHttp("/")) // Wait until elastic start
					.withEnv("xpack.security.enabled", "false")
					.withEnv("network.host", "_site_")
					.withEnv("network.publish_host", "_local_");

	@BeforeClass
	public static void setup() throws IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPut request = new HttpPut("http://localhost:9200/kvm");
		request.setHeader("Content-Type", "application/json");

		String payload = FileUtils.readFileToString(new File("src/test/resources/elasticsearch_mappings/kvm.json"), "UTF-8");
		StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_FORM_URLENCODED);
		request.setEntity(entity);

		HttpResponse response = httpClient.execute(request);
	}

	@Test
	public void contextLoads() {
		Assert.assertTrue(false);
	}
}
