package star.galaxy.engine.types


interface ForceApplicables : ForceApplicable {
    fun center(): WithPosition

    fun children(): Set<ForceApplicable>

    override fun position() = center().position()
}