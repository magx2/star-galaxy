package star.galaxy.engine.types

import star.galaxy.engine.metainformations.Newton
import star.galaxy.engine.metainformations.Radians

interface AngularApplicable {
    @Newton
    fun angularVelocity(): Double

    fun addAngularVelocity(@Newton angularVelocity: Double)

    @Radians
    fun orientation(): Double

    fun addOrientation(@Radians orientation: Double)

    fun inertia(): Double

    fun invertedInertia(): Double
}