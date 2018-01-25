package star.galaxy.engine.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import star.galaxy.engine.metainformations.Second

@Configuration
@ConfigurationProperties(prefix = "engine.clock")
class ClockProperties {
    val fps: Int = 60
    @Second
    val timeInGame: Double = 1.0
}