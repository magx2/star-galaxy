package star.galaxy.engine.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.ForceService
import star.galaxy.engine.types.ForceApplicable
import java.util.*
import javax.vecmath.Vector2d

@Service
internal class ForceServiceImpl : ForceService {
    private val forces: WeakHashMap<ForceApplicable, Vector2d> = WeakHashMap()

    override fun get(obj: ForceApplicable): Vector2d = forces.getOrPut(obj, { Vector2d() })

    override fun clear() {
        forces.values
                .stream()
                .forEach {
                    it.x = 0.0
                    it.y = 0.0
                }
    }
}