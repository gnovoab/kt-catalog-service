package com.gnovoab.example.demo.service

import com.gnovoab.example.demo.domain.model.Product

interface ProductService {
    fun fetchProducts(): Iterable<Product>
    fun fetchActiveProducts(): Iterable<Product>
    fun findProduct(id: Long): Product
    fun save(product: Product): Product
    fun delete(id: Long)
}
