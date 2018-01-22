package star.galaxy.engine.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.Clock
import star.galaxy.engine.config.ClockProperties
import star.galaxy.engine.metainformations.Milli
import star.galaxy.engine.metainformations.Second
import java.util.concurrent.TimeUnit

@Service
internal class FpsClock(private val clockProperties: ClockProperties) : Clock {
    @Milli
    @Second
    var timestamp = System.currentTimeMillis()
    @Suppress("LeakingThis")
    @Milli
    @Second
    val timeToWait: Long = TimeUnit.SECONDS.toMillis(1) / clockProperties.fps

    override fun next(): Int {
        @Milli @Second val currentTimeMillis = System.currentTimeMillis()
        @Milli @Second val delta = currentTimeMillis - timestamp
        if (timeToWait - delta > 0) {
            TimeUnit.MILLISECONDS.sleep(timeToWait - delta)
        }
        timestamp = currentTimeMillis

        return clockProperties.timeInGame
    }
}