package star.galaxy.engine.physics

import star.galaxy.engine.metainformations.Immutable
import star.galaxy.engine.metainformations.Second
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.ForceApplicables
import javax.vecmath.Vector2d

interface ForceApplicator {
    fun apply(@Immutable force: Vector2d, obj: ForceApplicables, @Second Δt: Double)

    fun apply(@Immutable force: Vector2d, obj: ForceApplicable, @Second Δt: Double)
}