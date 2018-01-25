package star.galaxy.engine.physics

import star.galaxy.engine.metainformations.Second
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.ForceApplicables

interface ForceApplicator {
    fun apply(obj: ForceApplicables, @Second Δt: Double)

    fun apply(obj: ForceApplicable, @Second Δt: Double)
}