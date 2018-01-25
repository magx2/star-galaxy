package star.galaxy.engine.impl

import star.galaxy.engine.Universe
import star.galaxy.engine.UniverseConstants
import star.galaxy.engine.entites.Galaxy
import star.galaxy.engine.entites.Player
import star.galaxy.engine.utils.MultiList

class BasicUniverse(private val galaxies: List<Galaxy>,
                    private val players: List<Player>) : Universe {
    private val withSpaceEngines = MultiList(players)

    override fun universeConstants() = UniverseConstants()

    override fun allForceApplicables() = galaxies

    override fun withForceGenerators() = withSpaceEngines

    override fun angularApplicable() = withSpaceEngines
}
