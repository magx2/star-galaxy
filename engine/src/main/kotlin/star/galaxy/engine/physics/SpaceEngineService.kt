package star.galaxy.engine.physics

import star.galaxy.engine.UniverseConstants
import star.galaxy.engine.metainformations.Newton
import star.galaxy.engine.types.AngularApplicable
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.WithForceGenerators
import javax.vecmath.Vector2d

interface SpaceEngineService {
    fun applyForceFromForceGenerators(objects: Set<WithForceGenerators>,
                                      velocityForces: Map<out ForceApplicable, Vector2d>,
                                      angularTorques: MutableMap<AngularApplicable, @Newton Double>,
                                      universeConstants: UniverseConstants)
}