package com.gnovoab.example.demo.service.impl

import com.gnovoab.example.demo.domain.model.Product
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
        LOGGER.info { "Fetching active products" }
        val products = productRespository.findByActiveTrue()
        LOGGER.info { "Returning active products" }
        return products
    }
}
