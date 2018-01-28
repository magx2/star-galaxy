package star.galaxy.engine.physics.impl

import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import star.galaxy.engine.AngularTorqueService
import star.galaxy.engine.ForceService
import star.galaxy.engine.metainformations.Testing
import star.galaxy.engine.types.ForceGenerator
import star.galaxy.engine.types.WithForceGenerators
import javax.vecmath.Point2d
import javax.vecmath.Vector2d


@RunWith(SpringRunner::class)
@Testing
class SpaceEngineServiceImplTest {
    @Autowired
    private lateinit var service: SpaceEngineServiceImpl

    @MockBean
    lateinit var forceService: ForceService
    @MockBean
    lateinit var angularTorqueService: AngularTorqueService

    @Before
    fun setUp() {
        whenever(forceService[com.nhaarman.mockito_kotlin.any()]).thenReturn(Vector2d())
    }

    @Test
    fun `should apply force to velocity`() {

        // given
        val initialVelocityForce = Vector2d(-4.0, 5.0)
        val velocityForce = Vector2d(3.0, 7.0)
        val obj = TestForceGenerator(velocityForce = velocityForce)

        val element = TestWithForceGenerators(setOf(obj))
        given(forceService[element]).willReturn(initialVelocityForce)

        // when
        service.apply(listOf(element))

        // then
        assertThat(initialVelocityForce.x).isEqualTo(-1.0)
        assertThat(initialVelocityForce.y).isEqualTo(12.0)
    }

    @Test
    fun `should apply torque`() {

        // given
        val angularForce = Vector2d(-2.0, 3.0)
        val vectorToParentCenterOfMass = Vector2d(5.0, -7.0)
        val obj = TestForceGenerator(angularForce = angularForce,
                vectorToParentCenterOfMass = vectorToParentCenterOfMass)
        val element = TestWithForceGenerators(setOf(obj))

        // when
        service.apply(listOf(element))

        // then
        verify(angularTorqueService).inc(element, 1.0)
    }

    private class TestWithForceGenerators(val forceGenerators: Set<ForceGenerator>) : WithForceGenerators {
        override fun forceGenerators() = forceGenerators

        override fun velocity() = Vector2d()

        override fun position() = Point2d()

        override fun mass() = 1.0

        override fun invertedMass() = 1.0

        override fun angularVelocity() = 1.0

        override fun addAngularVelocity(angularVelocity: Double) {
        }

        override fun orientation() = 0.0

        override fun addOrientation(orientation: Double) {
        }

        override fun inertia() = 1.0

        override fun invertedInertia() = 1.0
    }

    private class TestForceGenerator(private val velocityForce: Vector2d = Vector2d(),
                                     private val angularForce: Vector2d = Vector2d(),
                                     private val vectorToParentCenterOfMass: Vector2d = Vector2d()) : ForceGenerator {
        override fun velocityForce() = velocityForce

        override fun angularForce() = angularForce

        override fun vectorToParentCenterOfMass() = vectorToParentCenterOfMass
    }

}