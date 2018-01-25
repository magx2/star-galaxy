package star.galaxy.engine.types


interface ForceApplicables : ForceApplicable, SphereShape {
    fun children(): List<ForceApplicable>
}