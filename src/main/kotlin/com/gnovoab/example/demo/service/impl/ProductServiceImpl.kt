package com.gnovoab.example.demo.service.impl

import com.gnovoab.example.demo.domain.model.Product
import com.gnovoab.example.demo.respository.ProductRepository
import com.gnovoab.example.demo.service.ProductService
import org.springframework.stereotype.Service

/**
 * Class hat handles operations regarding products
 */
@Service
class ProductServiceImpl ( private val productRespository: ProductRepository): ProductService  {
    override fun fetchProducts(): Iterable<Product> {
        val products = productRespository.findByActiveTrue()
        return products
    }
}
