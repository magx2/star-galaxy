package star.galaxy.engine

import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.WithGravity

interface Universe {
    fun universeConstants(): UniverseConstants

    fun withGravity(): Set<WithGravity>

    fun forceApplicable(): Set<ForceApplicable>
}