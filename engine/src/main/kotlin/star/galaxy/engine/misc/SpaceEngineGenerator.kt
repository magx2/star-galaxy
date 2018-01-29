package star.galaxy.engine.misc

import star.galaxy.engine.metainformations.Immutable
import star.galaxy.engine.metainformations.Percentage
import star.galaxy.engine.types.ThrustForceGenerator
import javax.vecmath.Vector2d

interface SpaceEngineGenerator {
    fun create(@Percentage thrust: Double,
               @Immutable force: Vector2d,
               @Immutable vectorToParentCenterOfMass: Vector2d): ThrustForceGenerator
}