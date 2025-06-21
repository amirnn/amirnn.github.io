package com.amirnourinia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication   // no controllers needed; static handler is enough
class PersonalSiteApplication

fun main(args: Array<String>) {
    runApplication<PersonalSiteApplication>(*args)
}
