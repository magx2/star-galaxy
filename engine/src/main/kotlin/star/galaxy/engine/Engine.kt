package star.galaxy.engine

import star.galaxy.engine.metainformations.Second

interface Engine {
    fun nextStep(@Second timeDelta: Int)
}