package star.galaxy.engine

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import star.galaxy.engine.algebra.copy
import star.galaxy.engine.entites.*
import star.galaxy.engine.impl.BasicUniverse
import java.util.*
import javax.vecmath.Point2d

@Configuration
internal class UniverseLoader {
    @Bean
    fun universe(): Universe {
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

        val solarSystem = SolarSystem(Point(sun.position().copy()),
                listOf(sun, earth, mars, jupiter),
                SphereShapeImpl(100.0))

        val galaxy = Galaxy(
                listOf(solarSystem),
                Point(sun.position().copy())
        )

        return BasicUniverse(listOf(galaxy), listOf(player))
    }

    @Bean
    fun universeConstants() = universe().universeConstants()
}