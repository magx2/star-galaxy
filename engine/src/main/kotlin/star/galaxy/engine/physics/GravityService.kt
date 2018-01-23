package star.galaxy.engine.physics

import star.galaxy.engine.UniverseConstants
import star.galaxy.engine.metainformations.Newton
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.WithVelocity
import javax.vecmath.Vector2d

interface GravityService {
    fun applyGravity(objects: List<ForceApplicable>,
                     forces: Map<out WithVelocity, @Newton Vector2d>,
                     universeConstants: UniverseConstants)

    @Newton
    fun gravityForceValue(o1: ForceApplicable, o2: ForceApplicable, universeConstants: UniverseConstants): Double
}