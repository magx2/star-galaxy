package star.galaxy.engine.physics.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringRunner
import star.galaxy.engine.ForceService
import star.galaxy.engine.algebra.copy
import star.galaxy.engine.entites.Galaxy
import star.galaxy.engine.entites.ObjectInSpace
import star.galaxy.engine.entites.Point
import star.galaxy.engine.entities.ForceApplicablesImpl
import star.galaxy.engine.metainformations.Testing
import star.galaxy.engine.types.ForceApplicables
import javax.vecmath.Point2d
import javax.vecmath.Vector2d


@RunWith(SpringRunner::class)
@Testing
class NewtonsForceApplicatorTest {
    @Autowired
    private lateinit var service: NewtonsForceApplicator
    @Autowired
    private lateinit var forceService: ForceService

    @Test
    fun `should apply force to ForceApplicable`() {

        // given
        val initialForce = Vector2d(10.0, -20.0)
        val mass = 2.0
        val position = Point2d(30.0, -60.0)
        val velocity = Vector2d(-2.0, 7.0)
        val obj = ObjectInSpace(mass, position.copy(), velocity.copy())
        val Δt = 23

        forceService[obj].x = initialForce.x
        forceService[obj].y = initialForce.y
        val forceOnObj = initialForce.copy()
        forceOnObj.scale(obj.invertedMass())
        forceOnObj.scale(Δt.toDouble())

        val expectedVelocity = velocity.copy()
        expectedVelocity.add(forceOnObj)

        val expectedPosition = position.copy()
        expectedPosition.x += expectedVelocity.x * Δt
        expectedPosition.y += expectedVelocity.y * Δt

        // when
        service.apply(obj, Δt)

        // then
        assertThat(obj.velocity()).isEqualToComparingFieldByField(expectedVelocity)
        assertThat(obj.position()).isEqualToComparingFieldByField(expectedPosition)
        assertThat(forceService[obj]).isEqualToComparingFieldByField(initialForce)
    }

    @Test
    fun `should apply force to ForceApplicables`() {

        // given
        val initialForce = Vector2d(20.0, 30.0)
        val position = Point2d(-20.0, -10.0)
        val velocity = Vector2d()

        val v1 = Vector2d(514.0, 75.0)
        val v2 = Vector2d(768.0, 30.0)
        val v3 = Vector2d(405.0, 40.0)

        val p1 = Point2d(-10.0, 30.0)
        val p2 = Point2d(20.0, -30.0)
        val p3 = Point2d(-50.0, -20.0)

        val m1 = 6.0
        val m2 = 6.0
        val m3 = 6.0

        val r1 = 7.0
        val r2 = 11.0
        val r3 = 13.0

        val forceApplicables = listOf<ForceApplicables>(
                ForceApplicablesImpl(listOf(), v1.copy(), p1.copy(), m1, r1),
                ForceApplicablesImpl(listOf(), v2.copy(), p2.copy(), m2, r2),
                ForceApplicablesImpl(listOf(), v3.copy(), p3.copy(), m3, r3)
        )
        val obj = Galaxy(forceApplicables, Point(position), velocity.copy())
        val Δt = 2

        forceService[obj].x = initialForce.x
        forceService[obj].y = initialForce.y
        val forceOnObj = initialForce.copy()
        forceOnObj.scale(obj.invertedMass())
        forceOnObj.scale(Δt.toDouble())

        val expectedVelocity = velocity.copy()
        val expectedV1 = v1.copy()
        val expectedV2 = v2.copy()
        val expectedV3 = v3.copy()
        expectedVelocity.add(forceOnObj)
        expectedV1.add(forceOnObj)
        expectedV2.add(forceOnObj)
        expectedV3.add(forceOnObj)

        val expectedPosition = position.copy()
        val expectedP1 = p1.copy()
        val expectedP2 = p2.copy()
        val expectedP3 = p3.copy()
        expectedPosition.x += expectedVelocity.x * Δt
        expectedPosition.y += expectedVelocity.y * Δt
        expectedP1.x += expectedVelocity.x * Δt
        expectedP1.y += expectedVelocity.y * Δt
        expectedP2.x += expectedVelocity.x * Δt
        expectedP2.y += expectedVelocity.y * Δt
        expectedP3.x += expectedVelocity.x * Δt
        expectedP3.y += expectedVelocity.y * Δt

        // when
        service.apply(obj, Δt)

        // then
        assertThat(obj.velocity()).isEqualToComparingFieldByField(expectedVelocity)
        assertThat(obj.position()).isEqualToComparingFieldByField(expectedPosition)

        assertThat(forceApplicables[0].velocity()).isEqualToComparingFieldByField(expectedV1)
        assertThat(forceApplicables[0].position()).isEqualToComparingFieldByField(expectedP1)

        assertThat(forceApplicables[1].velocity()).isEqualToComparingFieldByField(expectedV2)
        assertThat(forceApplicables[1].position()).isEqualToComparingFieldByField(expectedP2)

        assertThat(forceApplicables[2].velocity()).isEqualToComparingFieldByField(expectedV3)
        assertThat(forceApplicables[2].position()).isEqualToComparingFieldByField(expectedP3)

        assertThat(forceService[obj]).isEqualToComparingFieldByField(initialForce)
    }
}