package star.galaxy.engine.physics.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.AngularTorqueService
import star.galaxy.engine.ForceService
import star.galaxy.engine.algebra.cross
import star.galaxy.engine.physics.SpaceEngineService
import star.galaxy.engine.types.WithForceGenerators

@Service
internal class SpaceEngineServiceImpl(private val forceService: ForceService,
                                      private val angularTorqueService: AngularTorqueService) : SpaceEngineService {
    override fun applyForceFromForceGenerators(objects: Collection<WithForceGenerators>) {
        objects.forEach {
            velocity(it)
            angular(it)
        }
    }

    private fun velocity(obj: WithForceGenerators) {
        val force = forceService[obj]
        obj.forceGenerators().forEach { force.add(it.velocityForce()) }
    }

    private fun angular(obj: WithForceGenerators) {
        val torque = obj.forceGenerators()
                .stream()
                .mapToDouble { it.vectorToParentCenterOfMass() cross it.angularForce() }
                .sum()
        angularTorqueService.inc(obj, torque)
    }
}
