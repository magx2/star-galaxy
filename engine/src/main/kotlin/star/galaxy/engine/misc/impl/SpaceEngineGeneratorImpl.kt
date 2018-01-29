package star.galaxy.engine.misc.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.algebra.copy
import star.galaxy.engine.algebra.cross
import star.galaxy.engine.algebra.perpendicular
import star.galaxy.engine.entites.SpaceEngine
import star.galaxy.engine.misc.SpaceEngineGenerator
import star.galaxy.engine.types.ThrustForceGenerator
import javax.vecmath.Vector2d
import kotlin.math.cos
import kotlin.math.sin

@Service
internal class SpaceEngineGeneratorImpl : SpaceEngineGenerator {
    override fun create(thrust: Double, force: Vector2d, vectorToParentCenterOfMass: Vector2d): ThrustForceGenerator {
        val alpha = force.angle(vectorToParentCenterOfMass)
        val cosAlpha = cos(alpha)
        val sinAlpha = sin(alpha)
        val forceLength = force.length()

        val velocityForce = vectorToParentCenterOfMass.copy()
        velocityForce.normalize()
        velocityForce.scale(forceLength * cosAlpha * thrust)

        val angularForce = perpendicular(vectorToParentCenterOfMass, force cross vectorToParentCenterOfMass)
        angularForce.normalize()
        angularForce.scale(forceLength * sinAlpha * thrust)

        return SpaceEngine(thrust, velocityForce, angularForce, vectorToParentCenterOfMass)
    }
}