package star.galaxy.engine.types

interface ForceApplicables {
    fun center(): WithPosition

    fun children(): Set<ForceApplicable>
}