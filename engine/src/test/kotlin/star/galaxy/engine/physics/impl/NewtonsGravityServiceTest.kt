package star.galaxy.engine.physics.impl

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringRunner
import star.galaxy.engine.ForceService
import star.galaxy.engine.UniverseConstants
import star.galaxy.engine.algebra.copy
import star.galaxy.engine.algebra.createVector
import star.galaxy.engine.entites.ObjectInSpace
import star.galaxy.engine.metainformations.Testing
import javax.vecmath.Point2d
import javax.vecmath.Vector2d
import kotlin.math.abs

@RunWith(SpringRunner::class)
@Testing
class NewtonsGravityServiceTest {
    val universeConstants = UniverseConstants()
    @Autowired
    lateinit var service: NewtonsGravityService
    @Autowired
    private lateinit var forceService: ForceService

    @Test
    fun `should count gravity force for 2 different objects`() {

        // given
        val o1 = ObjectInSpace(100.0, Point2d(-100.0, 50.0))
        val o2 = ObjectInSpace(30.0, Point2d(-250.0, -10.0))

        val r = o1.position().distance(o2.position())
        val expectedForce = universeConstants.G * (o1.mass() * o2.mass()) / r / r

        // when
        val forceValue = service.gravityForceValue(o1, o2)

        // then
        assertThat(forceValue).isEqualTo(expectedForce)
    }

    @Test
    fun `should return 0 if objects are the same`() {

        // given
        val o1 = ObjectInSpace(100.0, Point2d(-100.0, 50.0))

        // when
        val forceValue = service.gravityForceValue(o1, o1)

        // then
        assertThat(forceValue).isZero()
    }

    @Test
    fun `should return 0 if objects are in the same point`() {

        // given
        val o1 = ObjectInSpace(100.0, Point2d(-100.0, 50.0))
        val o2 = ObjectInSpace(30.0, Point2d(-100.0, 50.0))

        // when
        val forceValue = service.gravityForceValue(o1, o2)

        // then
        assertThat(forceValue).isZero()
    }

    @Test
    fun `should apply gravity forces to all objects`() {

        // given
        val o1 = ObjectInSpace(100.0, Point2d(-7.0, 5.0))
        val o2 = ObjectInSpace(30.0, Point2d(-4.0, -10.0))
        val o3 = ObjectInSpace(300.0, Point2d(10.0, 8.0))

        val f1Init = Vector2d(10.0, 15.0)
        val f2Init = Vector2d(100.0, 70.0)
        val f3Init = Vector2d()

        val f12 = service.gravityForceValue(o1, o2)
        val f13 = service.gravityForceValue(o1, o3)
        val f23 = service.gravityForceValue(o2, o3)

        val v12 = createVector(o1, o2)
        v12.normalize()
        v12.scale(f12)
        val v13 = createVector(o1, o3)
        v13.normalize()
        v13.scale(f13)

        val v21 = createVector(o2, o1)
        v21.normalize()
        v21.scale(f12)
        val v23 = createVector(o2, o3)
        v23.normalize()
        v23.scale(f23)

        val v31 = createVector(o3, o1)
        v31.normalize()
        v31.scale(f13)
        val v32 = createVector(o3, o2)
        v32.normalize()
        v32.scale(f23)

        val f1Out = f1Init.copy()
        f1Out.add(v12)
        f1Out.add(v13)

        val f2Out = f2Init.copy()
        f2Out.add(v21)
        f2Out.add(v23)

        val f3Out = f3Init.copy()
        f3Out.add(v31)
        f3Out.add(v32)

        forceService[o1].x = f1Init.x
        forceService[o1].y = f1Init.y
        forceService[o2].x = f2Init.x
        forceService[o2].y = f2Init.y
        forceService[o3].x = f3Init.x
        forceService[o3].y = f3Init.y

        val offsetValue = 0.01

        // when
        service.applyGravity(listOf(o1, o2, o3))

        // then
        val f1 = forceService[o1]
        assertThat(f1.x).isEqualTo(f1Out.x, offset(abs(offsetValue * f1Out.x)))
        assertThat(f1.y).isEqualTo(f1Out.y, offset(abs(offsetValue * f1Out.x)))

        val f2 = forceService[o2]
        assertThat(f2.x).isEqualTo(f2Out.x, offset(abs(offsetValue * f2Out.x)))
        assertThat(f2.y).isEqualTo(f2Out.y, offset(abs(offsetValue * f2Out.y)))

        val f3 = forceService[o3]
        assertThat(f3.x).isEqualTo(f3Out.x, offset(abs(offsetValue * f3Out.x)))
        assertThat(f3.y).isEqualTo(f3Out.y, offset(abs(offsetValue * f3Out.y)))
    }

    @Test
    fun `should apply gravity forces to all objects - 2 objects`() {

        // given
        val o1 = ObjectInSpace(2.0, Point2d(-7.0, 6.0))
        val o2 = ObjectInSpace(3.0, Point2d(-4.0, 1.0))

        val f1Init = Vector2d(10.0, 15.0)
        val f2Init = Vector2d(100.0, 70.0)

        val f12 = service.gravityForceValue(o1, o2)

        val v12 = createVector(o1, o2)
        v12.normalize()
        v12.scale(f12)

        val v21 = createVector(o2, o1)
        v21.normalize()
        v21.scale(f12)

        val f1Out = f1Init.copy()
        f1Out.add(v12)

        val f2Out = f2Init.copy()
        f2Out.add(v21)

        forceService[o1].x = f1Init.x
        forceService[o1].y = f1Init.y
        forceService[o2].x = f2Init.x
        forceService[o2].y = f2Init.y

        val offsetValue = 0.01

        // when
        service.applyGravity(listOf(o1, o2))

        // then
        val f1 = forceService[o1]
        assertThat(f1.x).isEqualTo(f1Out.x, offset(abs(offsetValue * f1Out.x)))
        assertThat(f1.y).isEqualTo(f1Out.y, offset(abs(offsetValue * f1Out.x)))

        val f2 = forceService[o2]
        assertThat(f2.x).isEqualTo(f2Out.x, offset(abs(offsetValue * f2Out.x)))
        assertThat(f2.y).isEqualTo(f2Out.y, offset(abs(offsetValue * f2Out.y)))
    }
}
