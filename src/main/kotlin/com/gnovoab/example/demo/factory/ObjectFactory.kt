
//Namespace
package com.gnovoab.example.demo.factory

import com.gnovoab.example.demo.domain.model.Product
import org.apache.commons.lang3.RandomStringUtils
import java.math.BigDecimal

class ObjectFactory {

    companion object {
        fun generateSampleProduct(): Product {
            val product = Product()

            product.name = RandomStringUtils.randomAlphabetic(6)
            product.price = BigDecimal.valueOf(RandomStringUtils.randomNumeric(2, 3).toDouble())
            product.sku = "TEST-" + RandomStringUtils.randomAlphanumeric(6)

            return product
        }

    }

}