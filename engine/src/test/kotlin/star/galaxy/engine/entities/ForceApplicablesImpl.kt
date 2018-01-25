package star.galaxy.engine.entities

import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.ForceApplicables
import javax.vecmath.Point2d
import javax.vecmath.Vector2d

data class ForceApplicablesImpl(private val children: List<ForceApplicable>,
                                private val velocity: Vector2d,
                                private val position: Point2d,
                                private val mass: Double,
                                private val radian: Double) : ForceApplicables {
    private val invertedMass = 1.0 / mass

    override fun children() = children

    override fun velocity() = velocity

    override fun position() = position

    override fun mass() = mass

    override fun invertedMass() = invertedMass

    override fun radian() = radian
}