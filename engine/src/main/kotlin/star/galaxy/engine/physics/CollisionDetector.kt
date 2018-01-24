package star.galaxy.engine.physics

import star.galaxy.engine.types.SphereShapeWithPosition

interface CollisionDetector {
    fun areColliding(o1: SphereShapeWithPosition, o2: SphereShapeWithPosition): Boolean
}