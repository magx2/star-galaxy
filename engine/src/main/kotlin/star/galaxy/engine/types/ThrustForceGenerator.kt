package star.galaxy.engine.types

import star.galaxy.engine.metainformations.Percentage


interface ThrustForceGenerator : ForceGenerator {
    @Percentage
    fun thrust(): Double

    fun newThrust(@Percentage thrust: Double)
}