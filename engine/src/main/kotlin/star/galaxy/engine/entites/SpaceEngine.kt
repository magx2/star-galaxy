package star.galaxy.engine.entites

import org.springframework.util.Assert.state
import star.galaxy.engine.metainformations.Newton
import star.galaxy.engine.metainformations.Percentage

class SpaceEngine(@Newton val thurst: Double,
                  @Percentage actualPower: Double = 0.0) {
    init {
        state(actualPower in 0.0..1.0, "actualPower: $actualPower")
    }

    @Percentage
    var actualPower: Double = actualPower
        set(value) {
            state(value in 0.0..1.0, "can't set actualPower to $value")
            field = value
        }
}