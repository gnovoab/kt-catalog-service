package com.gnovoab.example.demo.service.impl

import com.gnovoab.example.demo.domain.model.Product
import com.gnovoab.example.demo.exception.ResourceNotFoundException
import com.gnovoab.example.demo.respository.ProductRepository
import com.gnovoab.example.demo.service.ProductService
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val LOGGER = KotlinLogging.logger {}

/**
 * Class hat handles operations regarding products
 */
@Service
class ProductServiceImpl ( private val productRespository: ProductRepository): ProductService  {

    override fun fetchProducts(): Iterable<Product> {
        LOGGER.info { "Fetching active and inactive products" }
        val products = productRespository.findAll()
        LOGGER.info { "Returning all active and inactive products" }
        return products
    }

    override fun fetchActiveProducts(): Iterable<Product> {
        LOGGER.info { "Fetching active products" }
        val products = productRespository.findByActiveTrue()
        LOGGER.info { "Returning all active products" }
        return products
    }

    override fun findProduct(id: Long): Product {
        LOGGER.info { "Fetching product with id [$id]" }
        val product = productRespository
            .findById(id)
            .orElseThrow {
                LOGGER.warn { "Product with id [$id] not found" }
                ResourceNotFoundException("Product with id [$id] not found")
            }
        LOGGER.info { "Returning product info with id [$id]"}

        return product
    }
}
