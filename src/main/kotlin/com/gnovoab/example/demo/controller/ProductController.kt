package com.gnovoab.example.demo.controller

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
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Endpoint for Products
 */
@Tag(name = "PRODUCT", description = "Operations related to Product Catalog")
@RestController
@RequestMapping("/api/v1/products")
class ProductController(private val productService: ProductService) {

    /**
     * Fetch all products
     * @return
     */
    @Operation(summary = "All Products", description = "Fetch all active and inactive products", tags = ["product"])
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successful operation",
                content = [(Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = (ArraySchema(schema = Schema(implementation = Product::class)))
                ))]
            ),
            ApiResponse(
                responseCode = "500", description = "The service encountered a problem.",
                content = [(Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ApiErrorResponse::class)
                ))]
            )
        ]
    )
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun fetchAllProducts(): ResponseEntity<Iterable<Product>> {

        //Retrieve products
        val products:Iterable<Product> = productService.fetchProducts()

        //Return the products
        return ResponseEntity(products, HttpStatus.OK)
    }


    /**
     * Fetch active products
     * @return
     */
    @Operation(summary = "Active products only", description = "Fetch active products", tags = ["product"])
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successful operation",
                content = [(Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = (ArraySchema(schema = Schema(implementation = Product::class)))
                ))]
            ),
            ApiResponse(
                responseCode = "500", description = "The service encountered a problem.",
                content = [(Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ApiErrorResponse::class)
                ))]
            )
        ]
    )
    @GetMapping(value = ["/active"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun fetchActiveProducts(): ResponseEntity<Iterable<Product>> {

        //Retrieve products
        val products:Iterable<Product> = productService.fetchActiveProducts()

        //Return the products
        return ResponseEntity(products, HttpStatus.OK)
    }


    /**
     * Get specific product
     * @param id
     * @return
     */
    @Operation(summary = "Retrieve a product", description = "Gives you a product from a given id", tags = ["product"])
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful operation",
                content = [(Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = Product::class)
                ))]
            ),
            ApiResponse(responseCode = "400", description = "Malformed request syntax",
                content = [(Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ApiErrorResponse::class)
                ))]
            ),
            ApiResponse(responseCode = "404", description = "Resource not found",
                content = [(Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ApiErrorResponse::class)
                ))]
            ),
            ApiResponse( responseCode = "500", description = "The service encountered a problem.",
                content = [(Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ApiErrorResponse::class)
                ))]
            )
        ]
    )
    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun fetchProduct(@PathVariable @Valid id:Long): ResponseEntity<Product> {

        //Retrieve products
        val product:Product = productService.findProduct(id)

        //Return the products
        return ResponseEntity(product, HttpStatus.OK)
    }


}
