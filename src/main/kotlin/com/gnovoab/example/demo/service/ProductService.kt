package com.gnovoab.example.demo.service

import com.gnovoab.example.demo.domain.model.Product

interface ProductService {
    fun fetchProducts(): Iterable<Product>
}
