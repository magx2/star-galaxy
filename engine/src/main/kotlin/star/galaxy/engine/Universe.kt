package star.galaxy.engine

import star.galaxy.engine.types.AngularApplicable
import star.galaxy.engine.types.ForceApplicables
import star.galaxy.engine.types.WithForceGenerators

interface Universe {
    fun universeConstants(): UniverseConstants

    fun allForceApplicables(): List<ForceApplicables>

    fun withForceGenerators(): List<WithForceGenerators>

    fun angularApplicable(): List<AngularApplicable>
}