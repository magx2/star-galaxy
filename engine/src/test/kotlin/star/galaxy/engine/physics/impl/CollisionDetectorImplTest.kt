package star.galaxy.engine.physics.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import star.galaxy.engine.types.SphereShapeWithPosition
import javax.vecmath.Point2d

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class CollisionDetectorImplTest {
    @Autowired
    private lateinit var service: CollisionDetectorImpl

    @Test
    fun `should find that objects are colliding`() {

        // given
        val o1 = Circle(Point2d(6.0, -8.0), 2.0)
        val o2 = Circle(Point2d(2.0, -5.0), 3.0)

        // when
        val areColliding = service.areColliding(o1, o2)

        // then
        assertThat(areColliding).isTrue()
    }

    @Test
    fun `should not find that objects are colliding - moving away X`() {

        // given
        val o1 = Circle(Point2d(6.0, -8.0), 2.0)
        val o2 = Circle(Point2d(1.9, -5.0), 3.0)

        // when
        val areColliding = service.areColliding(o1, o2)

        // then
        assertThat(areColliding).isFalse()
    }

    @Test
    fun `should not find that objects are colliding - moving away Y`() {

        // given
        val o1 = Circle(Point2d(6.0, -8.1), 2.0)
        val o2 = Circle(Point2d(2.0, -5.0), 3.0)

        // when
        val areColliding = service.areColliding(o1, o2)

        // then
        assertThat(areColliding).isFalse()
    }

    @Test
    fun `should not find that objects are colliding - smaller radian`() {

        // given
        val o1 = Circle(Point2d(6.0, -8.0), 2.0)
        val o2 = Circle(Point2d(2.0, -5.0), 2.9)

        // when
        val areColliding = service.areColliding(o1, o2)

        // then
        assertThat(areColliding).isFalse()
    }

    private data class Circle(val position: Point2d,
                              val radian: Double) : SphereShapeWithPosition {
        override fun radian() = radian

        override fun position() = position
    }
}