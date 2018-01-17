package star.galaxy.engine.types

import star.galaxy.engine.metainformations.Mutable
import javax.vecmath.Vector2d

public interface WithVelocity {
    @Mutable
    fun velocity(): Vector2d
}