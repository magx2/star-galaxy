package star.galaxy.engine.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.Engine
import star.galaxy.engine.ForceService
import star.galaxy.engine.Universe
import star.galaxy.engine.metainformations.Second
import star.galaxy.engine.physics.ForceApplicator
import star.galaxy.engine.physics.GravityService
import star.galaxy.engine.types.AngularApplicable
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.ForceApplicables
import star.galaxy.engine.utils.castTo

@Service
class PhysicsEngine(private val gravityService: GravityService,
                    private val forceApplicator: ForceApplicator,
                    private val forceService: ForceService,
                    private val universe: Universe) : Engine {
    override fun nextStep(@Second Δt: Double) {
        applyGravityMaster(universe.allForceApplicables(), Δt)
        forceService.clear()
    }

    private fun applyGravityMaster(objs: List<ForceApplicable>, Δt: Double) {
        applyGravity(objs, Δt)
        objs.stream()
                .castTo(ForceApplicables::class.java)
                .forEach { applyGravityMaster(it.children(), Δt) }
    }

    private fun applyGravity(objs: List<ForceApplicable>, Δt: Double) {
        gravityService.applyGravity(objs)
        objs.forEach {
            when (it) {
                is ForceApplicables -> forceApplicator.apply(it, Δt)
                else -> forceApplicator.apply(it, Δt)
            }
        }
    }

    private fun applyAngularForces(forces: Map<AngularApplicable, Double>, @Second Δt: Double) {
        forces.forEach { (k, v) -> applyAngularForces(k, v, Δt) }
    }

    private fun applyAngularForces(obj: AngularApplicable, torque: Double, @Second Δt: Double) {
        obj.addAngularVelocity(torque * obj.invertedInertia() * Δt)
        obj.addOrientation(obj.angularVelocity() * Δt)
    }
}
