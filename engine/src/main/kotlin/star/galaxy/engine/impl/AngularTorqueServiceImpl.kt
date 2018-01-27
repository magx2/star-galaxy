package star.galaxy.engine.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.AngularTorqueService
import star.galaxy.engine.types.AngularApplicable
import java.util.*

@Service
internal class AngularTorqueServiceImpl : AngularTorqueService {
    private val angularTorques: MutableMap<AngularApplicable, Double> = HashMap()

    override fun get(obj: AngularApplicable): Double = angularTorques.getOrPut(obj, { 0.0 })

    override fun set(obj: AngularApplicable, value: Double) {
        angularTorques[obj] = value
    }

    override fun inc(obj: AngularApplicable, value: Double) {
        angularTorques.merge(obj, value, { a, b -> a + b })
    }

    override fun clear() {
        angularTorques.clear()
    }
}