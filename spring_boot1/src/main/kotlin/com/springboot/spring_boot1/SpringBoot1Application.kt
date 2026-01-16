package com.springboot.spring_boot1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBoot1Application

fun main(args: Array<String>) {
	runApplication<SpringBoot1Application>(*args)
}
