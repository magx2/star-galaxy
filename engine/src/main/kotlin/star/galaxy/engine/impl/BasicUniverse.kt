package star.galaxy.engine.impl

import star.galaxy.engine.Universe
import star.galaxy.engine.UniverseConstants
import star.galaxy.engine.entites.Orb
import star.galaxy.engine.types.ForceApplicable
import java.util.*

class BasicUniverse(orbs: Set<Orb>) : Universe {
    private val orbs: MutableSet<Orb> = orbs.toMutableSet()

    override fun universeConstants() = UniverseConstants()

    override fun forceApplicable(): Set<ForceApplicable> = Collections.unmodifiableSet(orbs)
}