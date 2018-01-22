package star.galaxy.engine.impl

import star.galaxy.engine.Universe
import star.galaxy.engine.UniverseConstants
import star.galaxy.engine.entites.Orb
import star.galaxy.engine.entites.Player
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.WithForceGenerators
import star.galaxy.engine.utils.MultiSet

class BasicUniverse(orbs: Set<Orb>,
                    players: Set<Player>) : Universe {
    private val orbs: MutableSet<Orb> = orbs.toMutableSet()
    private val players: MutableSet<Player> = players.toMutableSet()

    // generated
    private val forceApplicable: Set<ForceApplicable> = MultiSet(orbs, players)
    private val withSpaceEngines: Set<WithForceGenerators> = MultiSet(players)

    override fun universeConstants() = UniverseConstants()

    override fun forceApplicable() = forceApplicable

    override fun withForceGenerators() = withSpaceEngines

    override fun angularApplicable() = withSpaceEngines
}
