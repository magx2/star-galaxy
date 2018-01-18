package star.galaxy.engine.entites

import org.springframework.util.Assert
import star.galaxy.engine.metainformations.Meter
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.SphereShape
import star.galaxy.engine.types.WithUuid

data class Orb(private val identifiableObject: IdentifiableObject,
               private val objectInSpace: ObjectInSpace,
               @Meter private val radian: Double) : ForceApplicable by objectInSpace, WithUuid by identifiableObject, SphereShape {
    init {
        Assert.state(radian > 0.0, "radian: $radian")
    }

    override fun radian() = radian
}