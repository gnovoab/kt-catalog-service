
//Namespace
package com.gnovoab.example.demo.service

//Imports
import com.gnovoab.example.demo.domain.model.Product


interface ProductService {
    fun fetchProducts(): Iterable<Product>
}