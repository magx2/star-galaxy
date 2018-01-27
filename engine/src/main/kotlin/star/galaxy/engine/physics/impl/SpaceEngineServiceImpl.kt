package star.galaxy.engine.physics.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.algebra.cross
import star.galaxy.engine.physics.SpaceEngineService
import star.galaxy.engine.types.AngularApplicable
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.WithForceGenerators
import javax.vecmath.Vector2d

@Service
internal class SpaceEngineServiceImpl : SpaceEngineService {
    override fun applyForceFromForceGenerators(objects: Set<WithForceGenerators>,
                                               velocityForces: Map<out ForceApplicable, Vector2d>,
                                               angularTorques: MutableMap<AngularApplicable, Double>) {
        objects.forEach {
            velocity(it, velocityForces)
            angular(it, angularTorques)
        }
    }

    private fun velocity(obj: WithForceGenerators,
                         velocityForces: Map<out ForceApplicable, Vector2d>) {
        val force = velocityForces[obj]!!
        obj.forceGenerators().forEach { force.add(it.velocityForce()) }
    }

    private fun angular(obj: WithForceGenerators,
                        angularTorques: MutableMap<AngularApplicable, Double>) {
        val torque = angularTorques[obj]!! + obj.forceGenerators()
                .stream()
                .mapToDouble { it.vectorToParentCenterOfMass() cross it.angularForce() }
                .sum()
        angularTorques.put(obj, torque)
    }
}