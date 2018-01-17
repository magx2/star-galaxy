package star.galaxy.engine.types

import star.galaxy.engine.metainformations.Mutable
import javax.vecmath.Vector2d

interface WithPosition {
    @Mutable
    fun position(): Vector2d
}