package star.galaxy.engine.entites

import org.springframework.util.Assert.notEmpty
import star.galaxy.engine.metainformations.Immutable
import star.galaxy.engine.types.*
import javax.vecmath.Vector2d

class SolarSystem(private val position: WithPosition,
                  @Immutable private val children: List<ForceApplicable>,
                  private val sphereShape: SphereShape,
                  private val velocity: Vector2d = Vector2d()) :
        ForceApplicables,
        SphereShape by sphereShape,
        SphereShapeWithPosition,
        WithPosition by position {
    init {
        notEmpty(children, "children")
    }

    private val mass = children.stream().mapToDouble { it.mass() }.sum()
    private val invertedMass = 1 / mass

    override fun velocity() = velocity

    override fun mass() = mass

    override fun invertedMass() = invertedMass

    override fun children() = children
}
