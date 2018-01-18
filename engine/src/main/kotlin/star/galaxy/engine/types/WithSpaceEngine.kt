package star.galaxy.engine.types

import star.galaxy.engine.entites.SpaceEngine

interface WithSpaceEngine : WithAngle {
    fun spaceEngines(): Set<SpaceEngine>
}