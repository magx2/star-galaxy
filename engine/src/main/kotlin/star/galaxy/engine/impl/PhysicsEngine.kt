package star.galaxy.engine.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.AngularTorqueService
import star.galaxy.engine.Engine
import star.galaxy.engine.ForceService
import star.galaxy.engine.Universe
import star.galaxy.engine.metainformations.Second
import star.galaxy.engine.physics.ForceApplicator
import star.galaxy.engine.physics.GravityService
import star.galaxy.engine.physics.SpaceEngineService
import star.galaxy.engine.types.AngularApplicable
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.ForceApplicables
import star.galaxy.engine.utils.castTo

@Service
internal class PhysicsEngine(private val gravityService: GravityService,
                             private val forceApplicator: ForceApplicator,
                             private val forceService: ForceService,
                             private val spaceEngineService: SpaceEngineService,
                             private val angularTorqueService: AngularTorqueService,
                             private val universe: Universe) : Engine {
    override fun nextStep(@Second Δt: Int) {
        applyGravityMaster(universe.allForceApplicables(), Δt)
        spaceEngineService.apply(universe.withForceGenerators())
        applyAngularForces(universe.angularApplicable(), Δt)
        clear()
    }

    private fun applyGravityMaster(objs: List<ForceApplicable>, Δt: Int) {
        applyGravity(objs, Δt)
        objs.stream()
                .castTo(ForceApplicables::class.java)
                .forEach { applyGravityMaster(it.children(), Δt) }
    }

    private fun applyGravity(objs: List<ForceApplicable>, Δt: Int) {
        gravityService.applyGravity(objs)
        objs.forEach {
            when (it) {
                is ForceApplicables -> forceApplicator.apply(it, Δt)
                else -> forceApplicator.apply(it, Δt)
            }
        }
    }

    private fun applyAngularForces(angularApplicables: Collection<AngularApplicable>, @Second Δt: Int) {
        angularApplicables.forEach { applyAngularForces(it, Δt) }
    }

    private fun applyAngularForces(obj: AngularApplicable, @Second Δt: Int) {
        obj.addAngularVelocity(angularTorqueService[obj] * obj.invertedInertia() * Δt)
        obj.addOrientation(obj.angularVelocity() * Δt)
    }

    private fun clear() {
        forceService.clear()
        angularTorqueService.clear()
    }

}
