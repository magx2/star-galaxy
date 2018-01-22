package star.galaxy.engine

import star.galaxy.engine.metainformations.Meter
import star.galaxy.engine.metainformations.MeterSecond

data class UniverseConstants(val G: Double = 6.67408e-11,
                             val PI: Double = Math.PI,
                             @MeterSecond val speedOfLight: Double = 299_792_458.0,
                             @Meter val lightYear: Double = 9_460_730_472_580_800.0)