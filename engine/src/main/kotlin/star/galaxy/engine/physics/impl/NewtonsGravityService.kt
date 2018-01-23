package star.galaxy.engine.physics.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.UniverseConstants
import star.galaxy.engine.algebra.createVector
import star.galaxy.engine.metainformations.Newton
import star.galaxy.engine.physics.GravityService
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.WithVelocity
import javax.vecmath.Point2d
import javax.vecmath.Vector2d

@Service
class NewtonsGravityService : GravityService {
    override fun applyGravity(objects: Set<ForceApplicable>, forces: Map<out WithVelocity, Vector2d>, universeConstants: UniverseConstants) {
        objects.forEach { o1 ->
            objects.forEach { o2 ->
                //                    @Newton val gravityValue = computeGravity(o1, o2, universeConstants.G)
//
//                    applyGravity(o1.position(), o2.position(), gravityValue, forces[o1]!!)
//                    applyGravity(o2.position(), o1.position(), gravityValue, forces[o2]!!)
            }
        }
    }

    override fun gravityForceValue(o1: ForceApplicable, o2: ForceApplicable, universeConstants: UniverseConstants) =
            if (o1 !== o2) {
                val r = o1.position().distance(o2.position())
                if (r > 0.0) {
                    val masses = o1.mass() * o2.mass()
                    universeConstants.G * masses / r / r
                } else {
                    0.0
                }
            } else {
                0.0
            }

    private fun applyGravity(p1: Point2d, p2: Point2d, @Newton gravityValue: Double, force: Vector2d) {
        val gravityForce = createVector(p2, p1)
        gravityForce.normalize()
        gravityForce.scale(gravityValue)
        force.add(gravityForce)
    }
}