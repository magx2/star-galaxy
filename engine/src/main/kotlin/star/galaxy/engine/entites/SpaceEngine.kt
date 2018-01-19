package star.galaxy.engine.entites

import star.galaxy.engine.algebra.copy
import star.galaxy.engine.metainformations.Immutable
import star.galaxy.engine.metainformations.Percentage
import star.galaxy.engine.types.ThrustForceGenerator
import star.galaxy.engine.utils.checkPercentage
import javax.vecmath.Vector2d
import kotlin.math.cos
import kotlin.math.sin

class SpaceEngine(@Percentage private var thrust: Double,
                  @Immutable private val force: Vector2d,
                  @Immutable private val vectorToParentCenterOfMass: Vector2d) : ThrustForceGenerator {
    private val velocityForce: Vector2d
    private val angularForce: Vector2d

    init {
        thrust.checkPercentage()

        val alpha = force.angle(vectorToParentCenterOfMass)
        val cosAlpha = cos(alpha)
        val sinAlpha = sin(alpha)

        velocityForce = force.copy()
        velocityForce.scale(cosAlpha * thrust)

        angularForce = force.copy()
        angularForce.scale(sinAlpha * thrust)
    }

    @Immutable override fun velocityForce() = velocityForce

    @Immutable override fun angularForce() = angularForce

    @Immutable
    override fun vectorToParentCenterOfMass() = vectorToParentCenterOfMass

    @Percentage override fun thrust() = thrust

    override fun newThrust(@Percentage thrust: Double) {
        thrust.checkPercentage()
        val deltaThrust = thrust - this.thrust
        velocityForce.scale(deltaThrust)
        angularForce.scale(deltaThrust)
        this.thrust = thrust
    }
}