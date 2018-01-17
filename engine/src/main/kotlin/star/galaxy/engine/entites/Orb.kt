package star.galaxy.engine.entites

import org.springframework.util.Assert
import star.galaxy.engine.metainformations.Gram
import star.galaxy.engine.metainformations.Kilo
import star.galaxy.engine.metainformations.Meter
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.WithGravity
import javax.vecmath.Point2d
import javax.vecmath.Vector2d

data class Orb(@Kilo @Gram private val mass: Double,
               @Meter private val radian: Double,
               private val position: Point2d,
               private val velocity: Vector2d = Vector2d()) : WithGravity, ForceApplicable {
    private val invertedMass = 1 / mass

    init {
        Assert.state(mass > 0.0, "mass: $mass")
        Assert.state(radian > 0.0, "radian: $radian")
    }

    override fun mass() = mass

    override fun invertedMass() = invertedMass

    override fun position() = position

    override fun velocity() = velocity
}