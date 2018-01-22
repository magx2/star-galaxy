package star.galaxy.engine.entites

import star.galaxy.engine.metainformations.Meter
import star.galaxy.engine.types.SphereShape
import star.galaxy.engine.utils.checkPositive

internal class SphereShapeImpl(@Meter private val radian: Double) : SphereShape {
    init {
        radian.checkPositive()
    }

    override fun radian() = radian
}