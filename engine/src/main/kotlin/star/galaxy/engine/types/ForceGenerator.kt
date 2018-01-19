package star.galaxy.engine.types

import star.galaxy.engine.metainformations.Immutable
import javax.vecmath.Vector2d

interface ForceGenerator {
    @Immutable
    fun velocityForce(): Vector2d

    @Immutable
    fun angularForce(): Vector2d

    @Immutable
    fun vectorToParentCenterOfMass(): Vector2d
}