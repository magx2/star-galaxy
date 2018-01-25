package star.galaxy.engine.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.Engine
import star.galaxy.engine.ForceService
import star.galaxy.engine.Universe
import star.galaxy.engine.metainformations.Second
import star.galaxy.engine.physics.ForceApplicator
import star.galaxy.engine.physics.GravityService
import star.galaxy.engine.physics.SpaceEngineService
import star.galaxy.engine.types.AngularApplicable

@Service
class PhysicsEngine(private val gravityService: GravityService,
                    private val spaceEngineService: SpaceEngineService,
                    private val forceApplicator: ForceApplicator,
                    private val forceService: ForceService,
                    private val universe: Universe) : Engine {
    override fun nextStep(@Second Δt: Int) {
        val allForceApplicables = universe.allForceApplicables()

        gravityService.applyGravity(
                allForceApplicables)

        allForceApplicables.forEach { forceApplicator.apply(it, Δt.toDouble()) }
        forceService.clear()
    }

    private fun applyAngularForces(forces: Map<AngularApplicable, Double>, @Second Δt: Int) {
        forces.forEach { (k, v) -> applyAngularForces(k, v, Δt) }
    }

    private fun applyAngularForces(obj: AngularApplicable, torque: Double, @Second Δt: Int) {
        obj.addAngularVelocity(torque * obj.invertedInertia() * Δt)
        obj.addOrientation(obj.angularVelocity() * Δt)
    }
}
