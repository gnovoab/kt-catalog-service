
//Namespace
package com.gnovoab.example.demo.domain.api

//Imports
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

/**
 * Class that represents the system response when an error ocur
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiErrorResponse: ApiMessageResponse{

    constructor(status: HttpStatus, message: String): super(status, message){

    }

    constructor(status: HttpStatus, message: String, errors: List<String>): super(status, message) {

    }
}