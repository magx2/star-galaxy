package star.galaxy.engine.entites

import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.WithUuid

data class Player(private val identifiableObject: IdentifiableObject,
                  private val objectInSpace: ObjectInSpace) : ForceApplicable by objectInSpace, WithUuid by identifiableObject