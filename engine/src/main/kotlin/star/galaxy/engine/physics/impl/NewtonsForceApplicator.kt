package star.galaxy.engine.physics.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.ForceService
import star.galaxy.engine.physics.ForceApplicator
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.ForceApplicables

@Service
internal class NewtonsForceApplicator(private val forceService: ForceService) : ForceApplicator {
    override fun apply(obj: ForceApplicables, Δt: Int) {
        val invertedMass = obj.invertedMass()
        val force = forceService[obj]
        val vx = force.x * invertedMass * Δt
        val vy = force.y * invertedMass * Δt
        obj.velocity().x += vx
        obj.velocity().y += vy
        obj.children().forEach {
            it.velocity().x += vx
            it.velocity().y += vy
        }

        val px = obj.velocity().x * Δt
        val py = obj.velocity().y * Δt
        obj.position().x += px
        obj.position().y += py
        obj.children().forEach {
            it.position().x += px
            it.position().y += py
        }
    }

    override fun apply(obj: ForceApplicable, Δt: Int) {
        val invertedMass = obj.invertedMass()
        val force = forceService[obj]
        obj.velocity().x += force.x * invertedMass * Δt
        obj.velocity().y += force.y * invertedMass * Δt

        obj.position().x += obj.velocity().x * Δt
        obj.position().y += obj.velocity().y * Δt
    }
}