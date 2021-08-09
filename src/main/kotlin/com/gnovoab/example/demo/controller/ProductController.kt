
//Namespace
package com.gnovoab.example.demo.controller

//Imports
import com.gnovoab.example.demo.domain.api.ApiErrorResponse
import com.gnovoab.example.demo.domain.model.Product
import com.gnovoab.example.demo.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Endpoint for Products
 */
@Tag(name = "PRODUCT", description = "Everything regarding product catalog operations")
@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productService: ProductService
) {

    /**
     * Fetch active products
     * @return
     */
    @Operation(summary = "Fetch active products", description = "Active products", tags = ["product"])
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successful operation",
                content = [(Content(
                    mediaType = "application/json",
                    array = (ArraySchema(schema = Schema(implementation = Product::class)))
                ))]
            ),
            ApiResponse(
                responseCode = "500", description = "The service encountered a problem.",
                content = [(Content(
                    mediaType = "application/json",
                    array = (ArraySchema(schema = Schema(implementation = ApiErrorResponse::class)))
                ))]
            )
        ]
    )
    @GetMapping(value = ["/active"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun fetchActiveProducts(): ResponseEntity<Iterable<Product>> {

        //Retrieve products
        val products:Iterable<Product> = productService.fetchProducts()

        //Return the products
        return ResponseEntity(products, HttpStatus.OK)
    }

}