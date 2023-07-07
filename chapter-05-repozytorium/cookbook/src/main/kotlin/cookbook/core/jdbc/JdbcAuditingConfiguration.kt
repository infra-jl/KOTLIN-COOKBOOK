package cookbook.core.jdbc

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing
import java.time.Clock
import java.time.OffsetDateTime
import java.util.Optional

@Configuration
@EnableJdbcAuditing(dateTimeProviderRef = "offsetDateTimeProvider")
class JdbcAuditingConfiguration {

    @Bean
    fun offsetDateTimeProvider(clock: Clock): DateTimeProvider =
        DateTimeProvider { Optional.of(OffsetDateTime.now(clock)) }

}
