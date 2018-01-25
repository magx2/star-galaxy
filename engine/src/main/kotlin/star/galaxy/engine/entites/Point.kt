package star.galaxy.engine.entites

import star.galaxy.engine.types.WithPosition
import javax.vecmath.Point2d

data class Point(private val position: Point2d) : WithPosition {
    override fun position() = position
}