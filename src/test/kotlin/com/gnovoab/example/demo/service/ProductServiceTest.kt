
//Namespace
package com.gnovoab.example.demo.service

//Imports
import com.gnovoab.example.demo.factory.ObjectFactory
import com.gnovoab.example.demo.respository.ProductRepository
import com.gnovoab.example.demo.service.impl.ProductServiceImpl
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.test.context.ActiveProfiles

/**
 * Unit Test Class
 */
@ActiveProfiles("unitTest")
class ProductServiceTest {

    @Test
    internal fun fetchProductsTest() {
        //Create Mock
        val productRepository : ProductRepository = mock()

        //Inject Mock
        val productService = ProductServiceImpl(productRepository)

        //Create object to be returned
        val products = arrayListOf(ObjectFactory.generateSampleProduct(), ObjectFactory.generateSampleProduct(), ObjectFactory.generateSampleProduct())

        //Set behaviour
        whenever(productRepository.findByActiveTrue()).thenReturn(products)

        //Execute
        productService.fetchProducts()

        //Verify
        verify(productRepository, times(1)).findByActiveTrue()

    }
}