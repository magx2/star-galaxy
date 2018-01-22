package star.galaxy.engine.types

interface ForceApplicables : WithMass {
    fun center(): WithPosition

    fun children(): Set<ForceApplicable>
}