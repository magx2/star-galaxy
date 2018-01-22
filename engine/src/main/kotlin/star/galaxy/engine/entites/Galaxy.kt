package star.galaxy.engine.entites

import star.galaxy.engine.metainformations.Immutable
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.ForceApplicables
import star.galaxy.engine.types.SphereShape
import star.galaxy.engine.types.WithPosition
import javax.vecmath.Vector2d

class Galaxy(private val center: WithPosition,
             @Immutable private val children: Set<ForceApplicable>,
             private val sphereShape: SphereShape,
             private val velocity: Vector2d = Vector2d()) :
        ForceApplicables,
        SphereShape by sphereShape {
    private val mass = children.stream().mapToDouble { it.mass() }.sum()
    private val invertedMass = 1 / mass

    override fun velocity() = velocity

    override fun mass() = mass

    override fun invertedMass() = invertedMass

    override fun center() = center

    override fun children() = children
}
