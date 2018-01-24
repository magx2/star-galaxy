package star.galaxy.engine.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.Universe
import star.galaxy.engine.UniverseLoader
import star.galaxy.engine.entites.*
import java.util.*
import javax.vecmath.Point2d

@Service
internal class StubUniverseLoader : UniverseLoader {
    override fun load(): Universe {
        val sun = Orb(
                IdentifiableObject(UUID.randomUUID().toString(),
                        "sun"),
                ObjectInSpace(1_000_000.0,
                        Point2d()),
                SphereShapeImpl(100_000.0))
        val earth = Orb(
                IdentifiableObject(UUID.randomUUID().toString(),
                        "earth"),
                ObjectInSpace(10_000.0,
                        Point2d(10_000.0, 20_000.0)),
                SphereShapeImpl(5_000.0))
        val mars = Orb(
                IdentifiableObject(UUID.randomUUID().toString(),
                        "mars"),
                ObjectInSpace(5_000.0,
                        Point2d(-10_000.0, 5_000.0)),
                SphereShapeImpl(5_000.0))
        val jupiter = Orb(
                IdentifiableObject(UUID.randomUUID().toString(),
                        "jupiter"),
                ObjectInSpace(500_000.0,
                        Point2d(-50_000.0, -70_000.0)),
                SphereShapeImpl(50_000.0))
        val player = Player(
                IdentifiableObject(UUID.randomUUID().toString(), "player"),
                ObjectInSpace(1_000.0, Point2d(3_000.0, 3_000.0)),
                100.0,
                SphereShapeImpl(300_000.0)
        )

        return BasicUniverse(setOf(sun, earth, mars, jupiter), setOf(player))
    }

    override fun save(universe: Universe) = Unit
}