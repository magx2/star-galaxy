package star.galaxy.engine.utils

import org.springframework.util.Assert.state

fun Double.checkPercentage(): Double {
    state(this in 0.0..1.0, "Not a percentage! $this")
    return this
}

fun Double.checkPositive(): Double {
    state(this > 0.0, "Not positive! $this")
    return this
}