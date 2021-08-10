package com.gnovoab.example.demo.domain.rest

import com.gnovoab.example.demo.domain.model.Product
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

/**
 * Class that represents the request payload for create a Product
 */
class CreateProductRequest(
    @NotNull val product: Product,
    @NotNull @Positive val quantity:Int
) {

}
