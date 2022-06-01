package r97.lucy.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class LucyApiApplication

fun main(args: Array<String>) {
	runApplication<LucyApiApplication>(*args)
}
