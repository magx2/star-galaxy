package star.galaxy.engine.entites

import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.SphereShape
import star.galaxy.engine.types.SphereShapeWithPosition
import star.galaxy.engine.types.WithUuid

data class Orb(private val identifiableObject: IdentifiableObject,
               private val objectInSpace: ObjectInSpace,
               private val sphereShape: SphereShape) :
        ForceApplicable by objectInSpace,
        WithUuid by identifiableObject,
        SphereShape by sphereShape,
        SphereShapeWithPosition