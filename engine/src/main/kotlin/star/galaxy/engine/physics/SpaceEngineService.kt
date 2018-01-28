package star.galaxy.engine.physics

import star.galaxy.engine.types.WithForceGenerators

interface SpaceEngineService {
    fun apply(objects: Collection<WithForceGenerators>)
}