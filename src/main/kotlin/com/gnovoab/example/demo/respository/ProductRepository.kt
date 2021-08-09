
package com.gnovoab.example.demo.respository

import com.gnovoab.example.demo.domain.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product, Long> {
    fun findByActiveTrue(): Iterable<Product>
}
