package star.galaxy.engine.physics

import star.galaxy.engine.types.WithForceGenerators

interface SpaceEngineService {
    fun applyForceFromForceGenerators(objects: Collection<WithForceGenerators>)
}