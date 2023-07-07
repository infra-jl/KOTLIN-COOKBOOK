package cookbook.core.clock

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
class ClockConfiguration {

    @Bean
    fun clock(): Clock =
        Clock.systemDefaultZone()

}
