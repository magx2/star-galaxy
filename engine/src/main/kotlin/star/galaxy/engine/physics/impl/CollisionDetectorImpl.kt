package star.galaxy.engine.physics.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.physics.CollisionDetector
import star.galaxy.engine.types.SphereShapeWithPosition

@Service
internal class CollisionDetectorImpl : CollisionDetector {
    override fun areColliding(o1: SphereShapeWithPosition, o2: SphereShapeWithPosition): Boolean {
        val r = o1.radian() + o2.radian()
        return o1.position().distanceSquared(o2.position()) <= r * r
    }
}