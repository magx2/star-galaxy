package star.galaxy.engine.entites

import org.springframework.util.Assert
import star.galaxy.engine.metainformations.Gram
import star.galaxy.engine.metainformations.Kilo
import star.galaxy.engine.metainformations.Meter
import star.galaxy.engine.metainformations.Mutable
import star.galaxy.engine.types.WithMass
import star.galaxy.engine.types.WithPosition
import star.galaxy.engine.types.WithVelocity
import javax.vecmath.Vector2d

data class Orb(@Kilo @Gram private val mass: Double,
               @Meter private val radian: Double,
               private val position: Vector2d,
               private val velocity: Vector2d = Vector2d()) : WithMass, WithPosition, WithVelocity {
    private val invertedMass = 1 / mass

    init {
        Assert.state(mass > 0.0, "mass: $mass")
    }

    override fun mass() = mass

    override fun invertedMass() = invertedMass

    @Mutable override fun position() = position

    @Mutable override fun velocity() = velocity
}