package star.galaxy.engine.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.Universe
import star.galaxy.engine.UniverseLoader
import star.galaxy.engine.entites.Orb
import java.util.*
import javax.vecmath.Point2d

@Service
internal class StubUniverseLoader : UniverseLoader {
    override fun load(): Universe {
        val sun = Orb(
                UUID.randomUUID().toString(),
                "sun",
                1_000_000.0,
                100_000.0,
                Point2d())
        val earth = Orb(
                UUID.randomUUID().toString(),
                "earth",
                10_000.0,
                5_000.0,
                Point2d(10_000.0, 20_000.0))
        val mars = Orb(
                UUID.randomUUID().toString(),
                "mars",
                5_000.0,
                5_000.0,
                Point2d(-10_000.0, 5_000.0))
        val jupiter = Orb(
                UUID.randomUUID().toString(),
                "jupiter",
                500_000.0,
                50_000.0,
                Point2d(-50_000.0, -70_000.0))

        return BasicUniverse(setOf(sun, earth, mars, jupiter))
    }
}