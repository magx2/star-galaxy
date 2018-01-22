package star.galaxy.engine.entites

import star.galaxy.engine.metainformations.Meter
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.SphereShape
import star.galaxy.engine.types.WithUuid
import star.galaxy.engine.utils.checkPositive

data class Orb(private val identifiableObject: IdentifiableObject,
               private val objectInSpace: ObjectInSpace,
               @Meter private val radian: Double) : ForceApplicable by objectInSpace, WithUuid by identifiableObject, SphereShape {
    init {
        radian.checkPositive()
    }

    override fun radian() = radian
}