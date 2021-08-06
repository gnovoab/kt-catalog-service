package com.gnovoab.example.demo

import com.fasterxml.jackson.databind.JsonNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KtDemoApplicationTests {

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	@Test
	fun contextLoads() {
	}

	@Test
	fun healthOk() {
		val result = restTemplate.getForEntity("/actuator/health", JsonNode::class.java)
		Assertions.assertNotNull(result)
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode())
	}

	@Test
	fun swaggerOK() {
		val result: ResponseEntity<String> = restTemplate.getForEntity("/swagger-ui.html", String::class.java)
		Assertions.assertEquals(HttpStatus.OK, result.statusCode)
	}

}
