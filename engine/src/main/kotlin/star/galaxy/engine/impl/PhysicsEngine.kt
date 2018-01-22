package star.galaxy.engine.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.Engine
import star.galaxy.engine.Universe
import star.galaxy.engine.metainformations.Second
import star.galaxy.engine.physics.GravityService
import star.galaxy.engine.physics.SpaceEngineService
import star.galaxy.engine.types.AngularApplicable
import star.galaxy.engine.types.ForceApplicable
import javax.vecmath.Vector2d

@Service
class PhysicsEngine(private val gravityService: GravityService,
                    private val spaceEngineService: SpaceEngineService) : Engine {
    override fun nextStep(universe: Universe, @Second timeDelta: Int) {
        val velocityForces: Map<ForceApplicable, Vector2d> = universe.forceApplicable().map { it to Vector2d() }.toMap()
        val angularTorques: MutableMap<AngularApplicable, Double> = universe.angularApplicable().map { it to 1.0 }.toMap().toMutableMap()

        gravityService.applyGravity(
                universe.forceApplicable(),
                velocityForces,
                universe.universeConstants())
        spaceEngineService.applyForceFromForceGenerators(
                universe.withForceGenerators(),
                velocityForces,
                angularTorques,
                universe.universeConstants())

        applyVelocityForces(velocityForces, timeDelta)
        applyAngularForces(angularTorques, timeDelta)
    }

    private fun applyVelocityForces(forces: Map<ForceApplicable, Vector2d>, @Second timeDelta: Int) {
        forces.forEach { (k, v) -> applyVelocityForces(k, v, timeDelta) }
    }

    private fun applyVelocityForces(obj: ForceApplicable, force: Vector2d, @Second timeDelta: Int) {
        force.scale(obj.invertedMass()) // acceleraton
        force.scale(timeDelta.toDouble())
        obj.velocity().add(force)

        // reusing force vector to store copy of velocity in it
        force.x = obj.velocity().x
        force.y = obj.velocity().y

        force.scale(timeDelta.toDouble())
        obj.position().add(force)
    }

    private fun applyAngularForces(forces: Map<AngularApplicable, Double>, @Second timeDelta: Int) {
        forces.forEach { (k, v) -> applyAngularForces(k, v, timeDelta) }
    }

    private fun applyAngularForces(obj: AngularApplicable, torque: Double, @Second timeDelta: Int) {
        obj.addAngularVelocity(torque * obj.invertedInertia() * timeDelta)
        obj.addOrientation(obj.angularVelocity() * timeDelta)
    }
}