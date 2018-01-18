package star.galaxy.engine.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.Clock
import star.galaxy.engine.metainformations.Milli
import star.galaxy.engine.metainformations.Second
import java.util.concurrent.TimeUnit

@Service
internal class FpsClock : Clock {
    @Milli
    @Second
    var timestamp = System.currentTimeMillis()
    @Milli
    @Second
    val timeToWait: Long = TimeUnit.SECONDS.toMillis(1) / 60 // 60 FPS
    @Second
    val timeInGame = 1

    override fun next(): Int {
        val currentTimeMillis = System.currentTimeMillis()
        val delta = currentTimeMillis - timestamp
        if (timeToWait - delta > 0) {
            TimeUnit.MILLISECONDS.sleep(timeToWait - delta)
        }

        return timeInGame
    }
}