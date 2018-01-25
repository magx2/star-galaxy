package star.galaxy.engine.entites

import star.galaxy.engine.types.ForceApplicables
import star.galaxy.engine.types.WithPosition
import star.galaxy.engine.types.WithUuid
import java.util.*
import javax.vecmath.Vector2d
import kotlin.math.sqrt

class Galaxy(private val forceApplicables: List<ForceApplicables>,
             private val position: WithPosition,
             private val velocity: Vector2d = Vector2d(),
             private val uuid: String = UUID.randomUUID().toString()) :
        ForceApplicables,
        WithPosition by position,
        WithUuid {
    override fun children(): List<ForceApplicables> = forceApplicables

    override fun velocity() = velocity

    override fun radian() =
            sqrt(forceApplicables.stream()
                    .mapToDouble { it.position().distanceSquared(position.position()) }
                    .max()
                    .asDouble)

    override fun mass() =
            forceApplicables.stream()
                    .mapToDouble { it.mass() }
                    .sum()

    override fun invertedMass() =
            forceApplicables.stream()
                    .mapToDouble { it.invertedMass() }
                    .sum()

    override fun uuid() = uuid
}