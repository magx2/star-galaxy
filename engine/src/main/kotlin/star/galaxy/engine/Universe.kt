package star.galaxy.engine

import star.galaxy.engine.types.ForceApplicable

interface Universe {
    fun universeConstants(): UniverseConstants

    fun forceApplicable(): Set<ForceApplicable>
}