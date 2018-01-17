package star.galaxy.engine.physics

import star.galaxy.engine.UniverseConstants
import star.galaxy.engine.metainformations.Newton
import star.galaxy.engine.types.WithGravity
import star.galaxy.engine.types.WithVelocity
import javax.vecmath.Vector2d

interface GravityService {
    fun applyGravity(objects: Set<WithGravity>,
                     forces: Map<out WithVelocity, @Newton Vector2d>,
                     universeConstants: UniverseConstants)
}