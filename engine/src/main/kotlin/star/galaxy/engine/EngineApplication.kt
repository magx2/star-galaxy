package star.galaxy.engine

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class EngineApplication

fun main(args: Array<String>) {
    SpringApplication.run(EngineApplication::class.java, *args)
}
