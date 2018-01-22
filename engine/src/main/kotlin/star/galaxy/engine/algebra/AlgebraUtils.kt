package star.galaxy.engine.algebra

import star.galaxy.engine.metainformations.Radians
import javax.vecmath.Point2d
import javax.vecmath.Vector2d
import kotlin.math.cos
import kotlin.math.sin

fun createVector(p1: Point2d, p2: Point2d) = Vector2d(p2.x - p1.x, p2.y - p1.y)

fun Vector2d.copy() = Vector2d(this.x, this.y)

fun Vector2d.rotateVector(@Radians angle: Double): Vector2d {
    val cos = cos(angle)
    val sin = sin(angle)
    val x = cos * this.x - sin * this.y
    val y = sin * this.x + cos * this.y
    this.x = x
    this.y = y
    return this
}

infix fun Vector2d.cross(that: Vector2d) = this.x * that.y - this.y * that.x