package star.galaxy.engine

import star.galaxy.engine.metainformations.Second

interface Clock {
    @Second
    fun next(): Double

    @Second
    fun time(): Double
}