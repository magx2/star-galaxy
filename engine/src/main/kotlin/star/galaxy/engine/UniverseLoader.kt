package star.galaxy.engine

interface UniverseLoader {
    fun load(): Universe

    fun save(universe: Universe)
}