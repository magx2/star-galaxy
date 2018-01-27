package star.galaxy.engine

import star.galaxy.engine.metainformations.Clearable
import star.galaxy.engine.types.AngularApplicable

interface AngularTorqueService : Clearable {
    operator fun get(obj: AngularApplicable): Double

    operator fun set(obj: AngularApplicable, value: Double)

    fun inc(obj: AngularApplicable, value: Double)
}