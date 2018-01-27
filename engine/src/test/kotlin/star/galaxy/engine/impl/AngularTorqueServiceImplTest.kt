package star.galaxy.engine.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import star.galaxy.engine.types.AngularApplicable

@RunWith(SpringRunner::class)
@SpringBootTest
class AngularTorqueServiceImplTest {
    @Autowired
    private lateinit var service: AngularTorqueServiceImpl

    @Test
    fun `should increment already given value`() {

        // given
        val testObj = TestObj()

        // when
        service[testObj] = 5.0
        service.inc(testObj, 3.0)
        val value = service[testObj]

        // then
        assertThat(value).isEqualTo(8.0)
    }

    @Test
    fun `should insert incremented value if key is not present`() {

        // given
        val testObj = TestObj()

        // when
        service.inc(testObj, 3.0)
        val value = service[testObj]

        // then
        assertThat(value).isEqualTo(3.0)
    }

    @Test
    fun `should get zero if key is not present`() {

        // given
        val testObj = TestObj()

        // when
        val value = service[testObj]

        // then
        assertThat(value).isZero()
    }

    private class TestObj : AngularApplicable {
        override fun angularVelocity() = 0.0

        override fun addAngularVelocity(angularVelocity: Double) {}

        override fun orientation() = 0.0

        override fun addOrientation(orientation: Double) {
        }

        override fun inertia() = 0.0

        override fun invertedInertia() = 0.0
    }
}