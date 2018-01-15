package star.galaxy.root

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class RootApplication

fun main(args: Array<String>) {
    SpringApplication.run(RootApplication::class.java, *args)
}
