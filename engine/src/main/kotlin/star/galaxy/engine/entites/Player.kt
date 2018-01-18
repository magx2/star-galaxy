package star.galaxy.engine.entites

import star.galaxy.engine.metainformations.Radians
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.WithSpaceEngine
import star.galaxy.engine.types.WithUuid

data class Player(private val identifiableObject: IdentifiableObject,
                  private val objectInSpace: ObjectInSpace,
                  @Radians private val angle: Double = 0.0,
                  private val engines: Set<SpaceEngine> = setOf()) :
        ForceApplicable by objectInSpace,
        WithUuid by identifiableObject,
        WithSpaceEngine {
    override fun spaceEngines() = engines

    @Radians override fun angle() = angle
}