package star.galaxy.engine

import star.galaxy.engine.types.AngularApplicable
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.WithForceGenerators

interface Universe {
    fun universeConstants(): UniverseConstants

    fun forceApplicable(): Set<ForceApplicable>

    fun withForceGenerators(): Set<WithForceGenerators>

    fun angularApplicable(): Set<AngularApplicable>
}