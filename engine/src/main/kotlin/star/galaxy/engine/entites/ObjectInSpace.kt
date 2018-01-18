package star.galaxy.engine.entites

import org.springframework.util.Assert
import star.galaxy.engine.metainformations.Gram
import star.galaxy.engine.metainformations.Kilo
import star.galaxy.engine.types.ForceApplicable
import javax.vecmath.Point2d
import javax.vecmath.Vector2d

class ObjectInSpace(@Kilo @Gram private val mass: Double,
                    private val position: Point2d,
                    private val velocity: Vector2d = Vector2d()) : ForceApplicable {
    private val invertedMass = 1 / mass

    init {
        Assert.state(mass > 0.0, "mass: $mass")
    }

    override fun mass() = mass

    override fun invertedMass() = invertedMass

    override fun position() = position

    override fun velocity() = velocity
}