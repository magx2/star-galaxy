package star.galaxy.engine.physics.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.UniverseConstants
import star.galaxy.engine.algebra.createVector
import star.galaxy.engine.metainformations.Newton
import star.galaxy.engine.physics.GravityService
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.WithVelocity
import javax.vecmath.Vector2d

@Service
class NewtonsGravityService : GravityService {
    override fun applyGravity(objects: List<ForceApplicable>, forces: Map<out WithVelocity, Vector2d>, universeConstants: UniverseConstants) {
        for (idx1 in 0 until objects.size) {
            for (idx2 in idx1 until objects.size) {
                val o1 = objects[idx1]
                val o2 = objects[idx2]

                @Newton val gravityValue = gravityForceValue(o1, o2, universeConstants)

                if (gravityValue > 0.0) {
                    val force1 = forces[o1]!!
                    val force2 = forces[o2]!!

                    // applying force to o1
                    val gravityForce = createVector(o1, o2)
                    gravityForce.normalize()
                    gravityForce.scale(gravityValue)
                    force1.add(gravityForce)

                    // applying force to o2
                    gravityForce.negate()
                    force2.add(gravityForce)
                }
            }
        }
    }

    override fun gravityForceValue(o1: ForceApplicable, o2: ForceApplicable, universeConstants: UniverseConstants) =
            if (o1 !== o2) {
                val r = o1.position().distanceSquared(o2.position())
                if (r > 0.0) {
                    val masses = o1.mass() * o2.mass()
                    universeConstants.G * masses / r
                } else {
                    0.0
                }
            } else {
                0.0
            }
}