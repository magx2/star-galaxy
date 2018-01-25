package star.galaxy.engine.physics

import star.galaxy.engine.metainformations.Newton
import star.galaxy.engine.types.ForceApplicable

interface GravityService {
    fun applyGravity(objects: List<ForceApplicable>)

    @Newton
    fun gravityForceValue(o1: ForceApplicable, o2: ForceApplicable): Double
}