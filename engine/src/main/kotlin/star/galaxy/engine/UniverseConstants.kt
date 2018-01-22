package star.galaxy.engine

import star.galaxy.engine.metainformations.Meter
import star.galaxy.engine.metainformations.MeterSecond
import star.galaxy.engine.metainformations.Second

data class UniverseConstants(val G: Double = 6.67408e-11,
                             val PI: Double = Math.PI,
                             @MeterSecond val speedOfLight: Double = 299_792_458.0) {
    @Meter
    val lightYear: Double = speedOfLight * @Second 31_557_600
}
