package com.comcast.stringinator;

import com.comcast.stringinator.model.StringinatorInput;
import com.comcast.stringinator.model.StringinatorResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StringinatorApplicationTests {

	@Autowired
	private RestTemplate restTemplate;
	@LocalServerPort
	private int port;
	@Test
	void contextLoads() {
	}

	@Test
	public void testStringinate() {
		// Prepare the input
		StringinatorInput input = new StringinatorInput("Comcast is best place to develop!");
		String url = "http://localhost:" + port + "/stringinate";
		// Call the POST endpoint
		ResponseEntity<StringinatorResult> response = restTemplate.postForEntity(url, input, StringinatorResult.class);

		// Assert the response
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Comcast is best place to develop!", response.getBody().getInput());
	}

	@TestConfiguration
	static class TestConfig {
		@Bean
		public RestTemplate restTemplate(RestTemplateBuilder builder) {
			return builder.build();
		}
	}
}
