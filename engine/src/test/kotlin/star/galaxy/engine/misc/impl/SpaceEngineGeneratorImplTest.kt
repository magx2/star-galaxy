package star.galaxy.engine.misc.impl

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import star.galaxy.engine.metainformations.Testing
import javax.vecmath.Vector2d

@RunWith(SpringJUnit4ClassRunner::class)
@Testing
class SpaceEngineGeneratorImplTest {
    @Autowired
    private lateinit var service: SpaceEngineGeneratorImpl
    val force = Vector2d(4.0, 3.0)
    val vectorToParentCenterOfMass = Vector2d(0.0, 100.0)
    val thrust = .7
    val offset = Offset.offset(.0001)

    @Test
    fun `should generate ThrustForceGenerator with proper velocityForce`() {

        // when
        val engine = service.create(1.0, force, vectorToParentCenterOfMass)

        // then
        val velocityForce = engine.velocityForce()
        assertThat(velocityForce.angle(vectorToParentCenterOfMass)).isEqualTo(0.0, offset)
        assertThat(velocityForce.x).isEqualTo(0.0, offset)
        assertThat(velocityForce.y).isEqualTo(3.0, offset)
    }

    @Test
    fun `should generate ThrustForceGenerator with proper angularForce`() {

        // when
        val engine = service.create(1.0, force, vectorToParentCenterOfMass)

        // then
        val angularForce = engine.angularForce()
        assertThat(angularForce.dot(vectorToParentCenterOfMass)).isEqualTo(0.0, offset)
        assertThat(angularForce.x).isEqualTo(4.0, offset)
        assertThat(angularForce.y).isEqualTo(0.0, offset)
    }

    @Test
    fun `should apply trust to ThrustForceGenerator with proper velocityForce`() {

        // when
        val engine = service.create(thrust, force, vectorToParentCenterOfMass)

        // then
        assertThat(engine.velocityForce().length()).isEqualTo(thrust * 3.0, offset)
    }

    @Test
    fun `should apply trust to ThrustForceGenerator with proper angularForce`() {

        // when
        val engine = service.create(thrust, force, vectorToParentCenterOfMass)

        // then
        assertThat(engine.angularForce().length()).isEqualTo(4.0 * thrust, offset)
    }
}
