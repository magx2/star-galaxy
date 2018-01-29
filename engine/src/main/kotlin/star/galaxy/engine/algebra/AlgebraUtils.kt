package star.galaxy.engine.algebra

import star.galaxy.engine.metainformations.Direction
import star.galaxy.engine.metainformations.Radians
import star.galaxy.engine.metainformations.findDirection
import star.galaxy.engine.types.WithPosition
import javax.vecmath.Point2d
import javax.vecmath.Vector2d
import kotlin.math.cos
import kotlin.math.sin

fun createVector(p1: Point2d, p2: Point2d) = Vector2d(p2.x - p1.x, p2.y - p1.y)

fun createVector(p1: WithPosition, p2: WithPosition) = createVector(p1.position(), p2.position())

fun Vector2d.copy() = Vector2d(this.x, this.y)

fun Point2d.copy() = Point2d(this.x, this.y)

fun Vector2d.clear() {
    this.x = 0.0
    this.y = 0.0
}

fun Point2d.clear() {
    this.x = 0.0
    this.y = 0.0
}

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

fun perpendicular(vector: Vector2d, direction: Direction): Vector2d {
    val perpendicular = vector.copy()
    val signum = when (direction) {
        Direction.CLOCKWISE -> -1.0
        Direction.COUNTER_CLOCKWISE -> 1.0
    }
    val x = perpendicular.y * -signum
    val y = perpendicular.x * signum
    perpendicular.x = x
    perpendicular.y = y
    return perpendicular
}

fun perpendicular(vector: Vector2d, direction: Double) = perpendicular(vector, findDirection(direction))