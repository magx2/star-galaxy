package star.galaxy.engine.types

interface WithForceGenerators : ForceApplicable, AngularApplicable {
    fun forceGenerators(): Set<ForceGenerator>
}