package star.galaxy.engine.physics

import star.galaxy.engine.UniverseConstants
import star.galaxy.engine.metainformations.Newton
import star.galaxy.engine.types.ForceApplicable

interface GravityService {
    fun applyGravity(objects: List<ForceApplicable>, universeConstants: UniverseConstants)

    @Newton
    fun gravityForceValue(o1: ForceApplicable, o2: ForceApplicable, universeConstants: UniverseConstants): Double
}