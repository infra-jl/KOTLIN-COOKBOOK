package cookbook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CookbookApplication

fun main(args: Array<String>) {
    runApplication<CookbookApplication>(*args)
}
