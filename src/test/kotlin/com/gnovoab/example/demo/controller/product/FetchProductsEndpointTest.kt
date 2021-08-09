
//Namespace
package com.gnovoab.example.demo.controller.product

//Imports
import com.gnovoab.example.demo.domain.model.Product
import com.gnovoab.example.demo.factory.ObjectFactory
import com.gnovoab.example.demo.respository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.test.context.ActiveProfiles

/**
 * Integration Test Class
 */
@ActiveProfiles("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FetchProductsEndpointTest(
    @Autowired val restTemplate: TestRestTemplate,
    @Autowired val productRepository: ProductRepository) {

    companion object {
        const val BASE_URL = "/api/v1/products"
    }

    @Disabled("Disabled until is up!")
    @Test
    internal fun fetchProductsTest() {

        //Set the headers
        val requestHeaders = HttpHeaders()
        requestHeaders.contentType = MediaType.APPLICATION_JSON

        //Create the http request
        val request: HttpEntity<*> = HttpEntity<Any>(requestHeaders)

        //Invoke the API service
        val response: ResponseEntity<List<Product>> = restTemplate.exchange<List<Product>>(
                BASE_URL,
                HttpMethod.GET,
                request,
                object : ParameterizedTypeReference<List<Product?>?>() {})

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.statusCode)
        Assertions.assertNotNull(response.body)
    }


    @Test
    internal fun fetchActiveProductsTest() {

        //Get total of Rows
        val totalProductsInitial = productRepository.findByActiveTrue().count()

        //Prepopulate DB
        val product1: Product = ObjectFactory.generateSampleProduct()
        val product2: Product = ObjectFactory.generateSampleProduct()
        product2.active = false

        productRepository.save(product1)
        productRepository.save(product2)

        //Set the headers
        val requestHeaders = HttpHeaders()
        requestHeaders.contentType = MediaType.APPLICATION_JSON

        //Create the http request
        val request: HttpEntity<*> = HttpEntity<Any>(requestHeaders)

        //Invoke the API service
        val response = restTemplate.exchange<List<Product>>(
                BASE_URL + "/active",
                HttpMethod.GET,
                request,
                object : ParameterizedTypeReference<List<Product?>?>() {})

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.statusCode)
        Assertions.assertNotNull(response.body)
        Assertions.assertEquals((totalProductsInitial + 1).toLong(), response.body!!.count().toLong())
    }


    @Test
    internal fun fetchProductWrongPath() {
        //Set the headers
        val requestHeaders = HttpHeaders()
        requestHeaders.contentType = MediaType.APPLICATION_JSON

        //Create the http request
        val request: HttpEntity<*> = HttpEntity<Any>(requestHeaders)

        //Invoke the API service
        val response = restTemplate.exchange<Any>(BASE_URL + "/a", HttpMethod.GET, request, object : ParameterizedTypeReference<Any?>() {})

        //Verify
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }
}