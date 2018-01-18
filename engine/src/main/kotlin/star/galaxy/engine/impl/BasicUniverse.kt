package star.galaxy.engine.impl

import star.galaxy.engine.Universe
import star.galaxy.engine.UniverseConstants
import star.galaxy.engine.entites.Orb
import star.galaxy.engine.entites.Player
import star.galaxy.engine.types.ForceApplicable
import java.util.Collections.unmodifiableSet

class BasicUniverse(orbs: Set<Orb>,
                    players: Set<Player>) : Universe {
    private val orbs: MutableSet<Orb> = orbs.toMutableSet()
    private val players: MutableSet<Player> = players.toMutableSet()

    private val forceApplicable: Set<ForceApplicable> = joinSet(orbs, players)

    override fun universeConstants() = UniverseConstants()

    override fun forceApplicable(): Set<ForceApplicable> = forceApplicable
}

private fun <T> joinSet(vararg sets: Set<T>): Set<T> {
    val set = HashSet<T>()
    sets.forEach { set.addAll(it) }
    return unmodifiableSet(set)
}