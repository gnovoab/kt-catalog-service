package com.gnovoab.example.demo.service

import com.gnovoab.example.demo.domain.model.Product
import com.gnovoab.example.demo.exception.ResourceNotFoundException
import com.gnovoab.example.demo.factory.ObjectFactory
import com.gnovoab.example.demo.respository.ProductRepository
import com.gnovoab.example.demo.service.impl.ProductServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import org.springframework.test.context.ActiveProfiles
import java.util.*

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
        whenever(productRepository.findAll()).thenReturn(products)

        //Execute
        productService.fetchProducts()

        //Verify
        verify(productRepository, times(1)).findAll()
    }

    @Test
    internal fun fetchActiveProductsTest() {
        //Create Mock
        val productRepository : ProductRepository = mock()

        //Inject Mock
        val productService = ProductServiceImpl(productRepository)

        //Create object to be returned
        val products = arrayListOf(ObjectFactory.generateSampleProduct(), ObjectFactory.generateSampleProduct(), ObjectFactory.generateSampleProduct())

        //Set behaviour
        whenever(productRepository.findByActiveTrue()).thenReturn(products)

        //Execute
        productService.fetchActiveProducts()

        //Verify
        verify(productRepository, times(1)).findByActiveTrue()
    }

    @Test
    internal fun findProductByIdOkTest() {
        //Create Mock
        val productRepository : ProductRepository = mock()

        //Inject Mock
        val productService = ProductServiceImpl(productRepository)

        //Set behaviour
        whenever(productRepository.findById(any())).thenReturn(Optional.of(Product()))

        //Execute
        productService.findProduct(1L)

        //Verify
        verify(productRepository, times(1)).findById(any())
    }


    @Test
    internal fun findProductByIdFailedTest() {
        //Create Mock
        val productRepository : ProductRepository = mock()

        //Inject Mock
        val productService = ProductServiceImpl(productRepository)

        //Set behaviour
        whenever(productRepository.findById(any())).thenReturn(Optional.empty())

        //Execute
        Assertions.assertThrows(ResourceNotFoundException::class.java) {
            productService.findProduct(0)
        }

        //Verify
        verify(productRepository, times(1)).findById(any())

    }
}
