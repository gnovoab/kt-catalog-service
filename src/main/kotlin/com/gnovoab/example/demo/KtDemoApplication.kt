package com.gnovoab.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Main class
 */
@SpringBootApplication
class KtDemoApplication

fun main(args: Array<String>) {
	@Suppress("SpreadOperator")
	runApplication<KtDemoApplication>(*args)
}
