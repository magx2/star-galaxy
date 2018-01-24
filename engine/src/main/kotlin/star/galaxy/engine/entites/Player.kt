package star.galaxy.engine.entites

import star.galaxy.engine.metainformations.Newton
import star.galaxy.engine.metainformations.Radians
import star.galaxy.engine.types.*
import star.galaxy.engine.utils.checkPositive

data class Player(private val identifiableObject: IdentifiableObject,
                  private val objectInSpace: ObjectInSpace,
                  private val inertia: Double,
                  private val sphereShape: SphereShape,
                  @Radians private var orientation: Double = 0.0,
                  @Newton private var angularVelocity: Double = 0.0,
                  @Newton private var torque: Double = 0.0,
                  private val engines: Set<SpaceEngine> = setOf()) :
        ForceApplicable by objectInSpace,
        WithUuid by identifiableObject,
        WithThrustForceGenerators,
        SphereShape by sphereShape,
        SphereShapeWithPosition {

    init {
        inertia.checkPositive()
    }

    private val invertedInertia = 1.0 / inertia

    @Newton override fun angularVelocity() = angularVelocity

    override fun addAngularVelocity(angularVelocity: Double) {
        this.angularVelocity += angularVelocity
    }

    override fun orientation() = orientation

    override fun addOrientation(orientation: Double) {
        this.orientation += orientation
    }

    override fun inertia() = inertia

    override fun invertedInertia() = invertedInertia

    override fun forceGenerators() = engines
}