package com.ap;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.Wait;

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

	@Test
	public void contextLoads() {
		Assert.assertTrue(true);
	}

}
