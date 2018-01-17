package star.galaxy.engine.algebra

import javax.vecmath.Point2d
import javax.vecmath.Vector2d

fun createVector(p1: Point2d, p2: Point2d) = Vector2d(p2.x - p1.x, p2.y - p1.y)

fun Vector2d.copy() = Vector2d(this.x, this.y)
