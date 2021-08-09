
//Namespace
package com.gnovoab.example.demo.domain.api

import org.springframework.http.HttpStatus

/**
 * Class that represents the response messages when a 3rd party interact with our service
 */
@Suppress("EmptyClassBlock")
open class ApiMessageResponse (
    var status: HttpStatus,
    var message:String
    ) {


}
