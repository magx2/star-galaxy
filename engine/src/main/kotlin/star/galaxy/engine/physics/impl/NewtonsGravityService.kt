package star.galaxy.engine.physics.impl

import jdk.vm.ci.sparc.SPARC.o1
import jdk.vm.ci.sparc.SPARC.o2
import org.springframework.stereotype.Service
import star.galaxy.engine.UniverseConstants
import star.galaxy.engine.algebra.createVector
import star.galaxy.engine.metainformations.Newton
import star.galaxy.engine.physics.GravityService
import star.galaxy.engine.types.WithGravity
import star.galaxy.engine.types.WithPosition
import star.galaxy.engine.types.WithVelocity
import javax.vecmath.Point2d
import javax.vecmath.Vector2d

@Service
class NewtonsGravityService : GravityService {
    override fun applyGravity(objects: Set<WithGravity>, forces: Map<out WithVelocity, Vector2d>, universeConstants: UniverseConstants) {
        objects.forEach { o1 ->
            objects.forEach { o2 ->
                if (shouldComputeGravityForce(o1, o2)) {
                    @Newton val gravityValue = computeGravity(o1, o2, universeConstants.G)

                    applyGravity(o1.position(), o2.position(), gravityValue, forces[o1]!!)
                    applyGravity(o2.position(), o1.position(), gravityValue, forces[o2]!!)
                }
            }
        }
    }

    private fun shouldComputeGravityForce(o1: WithPosition, o2: WithPosition) =
            if (o1 !== o2) {
                o1.position().distance(o2.position()) > 0.0
            } else {
                false
            }

    private fun computeGravity(o1: WithGravity, o2: WithGravity, G: Double): Double {
        val masses = o1.mass() * o2.mass()
        val r = o1.position().distance(o2.position())
        return G * masses / r / r
    }

    private fun applyGravity(p1: Point2d, p2: Point2d, @Newton gravityValue: Double, force: Vector2d) {
        val gravityForce = createVector(p2, p1)
        gravityForce.normalize()
        gravityForce.scale(gravityValue)
        force.add(gravityForce)
    }
}