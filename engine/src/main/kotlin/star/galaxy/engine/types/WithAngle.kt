package star.galaxy.engine.types

import star.galaxy.engine.metainformations.Radians

interface WithAngle {
    @Radians
    fun angle(): Double
}