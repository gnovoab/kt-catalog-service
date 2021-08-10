package com.gnovoab.example.demo.controller.product

import com.gnovoab.example.demo.domain.model.Product
import com.gnovoab.example.demo.domain.rest.CreateProductRequest
import com.gnovoab.example.demo.factory.ObjectFactory
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.test.context.ActiveProfiles

/**
 * Integration Test Class
 */
@Suppress("ClassOrdering")
@ActiveProfiles("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateProductEndpoint (@Autowired val restTemplate: TestRestTemplate){

    companion object {
        const val BASE_URL = "/api/v1/products"
    }

    @Test
    internal fun saveProductOK() {

        //Create payload
        val productRequestPayload = CreateProductRequest(
           ObjectFactory.generateSampleProduct(),
           Integer.valueOf(RandomStringUtils.randomNumeric(1,3)))


        //Set the headers
        val requestHeaders = HttpHeaders()
        requestHeaders.contentType = MediaType.APPLICATION_JSON

        //Create the http request
        val request: HttpEntity<*> = HttpEntity<Any>(productRequestPayload, requestHeaders)

        //Invoke the API service
        val response = restTemplate.exchange<Product>(BASE_URL, HttpMethod.POST, request, object : ParameterizedTypeReference<Product?>() {})

        //Verify
        Assertions.assertEquals(HttpStatus.CREATED, response.statusCode)
        Assertions.assertNotNull(response.body)
        Assertions.assertTrue(response.body!!.name!!.length > 0)
        Assertions.assertTrue(response.body!!.price!!.toDouble() > 0)
    }

}
