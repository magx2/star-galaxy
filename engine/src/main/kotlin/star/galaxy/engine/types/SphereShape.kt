package star.galaxy.engine.types

import star.galaxy.engine.metainformations.Meter

interface SphereShape {
    @Meter
    fun radian(): Double
}