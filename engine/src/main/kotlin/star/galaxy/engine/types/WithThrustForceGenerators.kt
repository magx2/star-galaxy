package star.galaxy.engine.types

interface WithThrustForceGenerators : WithForceGenerators {
    override fun forceGenerators(): Set<ThrustForceGenerator>
}