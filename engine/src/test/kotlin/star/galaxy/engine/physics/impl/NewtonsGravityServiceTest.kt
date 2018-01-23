package star.galaxy.engine.physics.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import star.galaxy.engine.UniverseConstants
import star.galaxy.engine.entites.ObjectInSpace
import javax.vecmath.Point2d

@RunWith(SpringRunner::class)
@SpringBootTest
class NewtonsGravityServiceTest {
    val universeConstants = UniverseConstants()
    @Autowired
    lateinit var service: NewtonsGravityService

    @Test
    fun `should count gravity force for 2 different objects`() {

        // given
        val o1 = ObjectInSpace(100.0, Point2d(-100.0, 50.0))
        val o2 = ObjectInSpace(30.0, Point2d(-250.0, -10.0))

        val r = o1.position().distance(o2.position())
        val expectedForce = universeConstants.G * (o1.mass() * o2.mass()) / r / r

        // when
        val forceValue = service.gravityForceValue(o1, o2, universeConstants)

        // then
        assertThat(forceValue).isEqualTo(expectedForce)
    }

    @Test
    fun `should return 0 if objects are the same`() {

        // given
        val o1 = ObjectInSpace(100.0, Point2d(-100.0, 50.0))

        // when
        val forceValue = service.gravityForceValue(o1, o1, universeConstants)

        // then
        assertThat(forceValue).isZero()
    }

    @Test
    fun `should return 0 if objects are in the same point`() {

        // given
        val o1 = ObjectInSpace(100.0, Point2d(-100.0, 50.0))
        val o2 = ObjectInSpace(30.0, Point2d(-100.0, 50.0))

        // when
        val forceValue = service.gravityForceValue(o1, o2, universeConstants)

        // then
        assertThat(forceValue).isZero()
    }
}
