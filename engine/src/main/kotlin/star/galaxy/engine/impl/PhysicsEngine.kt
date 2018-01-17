package star.galaxy.engine.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import star.galaxy.engine.Engine
import star.galaxy.engine.Universe
import star.galaxy.engine.metainformations.Second
import star.galaxy.engine.physics.GravityService
import star.galaxy.engine.types.ForceApplicable
import javax.vecmath.Vector2d

@Service
class PhysicsEngine @Autowired constructor(private val universe: Universe,
                                           private val gravityService: GravityService) : Engine {
    override fun nextStep(@Second timeDelta: Int) {
        val forces = universe.forceApplicable().map { it to Vector2d() }.toMap()

        gravityService.applyGravity(universe.withGravity(), forces, universe.universeConstants())

        applyForces(forces, timeDelta)
    }

    private fun applyForces(forces: Map<ForceApplicable, Vector2d>, @Second timeDelta: Int) {
        forces.forEach { (k, v) -> applyForces(k, v, timeDelta) }
    }

    private fun applyForces(obj: ForceApplicable, force: Vector2d, @Second timeDelta: Int) {
        force.scale(obj.invertedMass()) // acceleraton
        force.scale(timeDelta.toDouble())
        obj.velocity().add(force)

        // reusing force vector to store copy of velocity in it
        force.x = obj.velocity().x
        force.y = obj.velocity().y

        force.scale(timeDelta.toDouble())
        obj.position().add(force)
    }
}