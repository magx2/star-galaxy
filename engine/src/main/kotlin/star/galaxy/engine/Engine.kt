package star.galaxy.engine

import star.galaxy.engine.metainformations.Second

interface Engine {
    fun nextStep(universe: Universe, @Second timeDelta: Int)
}