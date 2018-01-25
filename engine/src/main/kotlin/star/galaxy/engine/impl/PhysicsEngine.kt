package star.galaxy.engine.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.Engine
import star.galaxy.engine.Universe
import star.galaxy.engine.metainformations.Second
import star.galaxy.engine.physics.ForceApplicator
import star.galaxy.engine.physics.GravityService
import star.galaxy.engine.physics.SpaceEngineService
import star.galaxy.engine.types.AngularApplicable
import star.galaxy.engine.types.ForceApplicable
import javax.vecmath.Vector2d

@Service
class PhysicsEngine(private val gravityService: GravityService,
                    private val spaceEngineService: SpaceEngineService,
                    private val forceApplicator: ForceApplicator) : Engine {
    override fun nextStep(universe: Universe, @Second Δt: Int) {
        val allForceApplicables = universe.allForceApplicables()

        val velocityForces: Map<ForceApplicable, Vector2d> = allForceApplicables.map { it to Vector2d() }.toMap()

        gravityService.applyGravity(
                allForceApplicables,
                velocityForces,
                universe.universeConstants())

        allForceApplicables.forEach { forceApplicator.apply(velocityForces[it]!!, it, Δt.toDouble()) }
    }

    private fun applyAngularForces(forces: Map<AngularApplicable, Double>, @Second Δt: Int) {
        forces.forEach { (k, v) -> applyAngularForces(k, v, Δt) }
    }

    private fun applyAngularForces(obj: AngularApplicable, torque: Double, @Second Δt: Int) {
        obj.addAngularVelocity(torque * obj.invertedInertia() * Δt)
        obj.addOrientation(obj.angularVelocity() * Δt)
    }
}
