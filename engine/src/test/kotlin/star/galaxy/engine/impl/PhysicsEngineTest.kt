package star.galaxy.engine.impl

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import star.galaxy.engine.ForceService
import star.galaxy.engine.Universe
import star.galaxy.engine.physics.ForceApplicator
import star.galaxy.engine.physics.GravityService
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.ForceApplicables
import javax.vecmath.Point2d
import javax.vecmath.Vector2d


@RunWith(SpringRunner::class)
@SpringBootTest
class PhysicsEngineTest {
    @Autowired
    lateinit var physicsEngine: PhysicsEngine

    @MockBean
    lateinit var gravityService: GravityService
    @MockBean
    lateinit var forceApplicator: ForceApplicator
    @MockBean
    lateinit var forceService: ForceService
    @MockBean
    lateinit var universe: Universe

    val Δt: Double = 10.0

    @Test
    fun `should apply gravity forces to all objects`() {

        // given
        val root1 = createOneObjectLevel3()
        val root2 = createOneObjectLevel3()
        val root3 = createOneObjectLevel3()
        val root: List<ForceApplicables> = listOf(root1, root2, root3)

        given(universe.allForceApplicables()).willReturn(root)

        // when
        physicsEngine.nextStep(Δt)

        // then
        verify(gravityService).applyGravity(root)
        verifyRoot(root1)
        verifyRoot(root2)
        verifyRoot(root3)
        verify(forceService).clear()
    }

    private fun verifyRoot(root: ForceApplicables) {
        root.children()
                .forEach {
                    when (it) {
                        is ForceApplicables -> {
                            verify(forceApplicator).apply(it, Δt)
                            verifyRoot(it)
                        }
                        else -> verify(forceApplicator).apply(it, Δt)
                    }
                }
    }

    private fun createOneObjectLevel3() =
            Level3(listOf(
                    Level2(listOf(Level1(), Level1(), Level1())),
                    Level2(listOf(Level1(), Level1(), Level1())),
                    Level1()
            ))

    private class Level1 : ForceApplicable {
        private val id = ID++

        override fun toString() = "${javaClass.name}#$id"

        override fun velocity() = Vector2d()

        override fun position() = Point2d()

        override fun mass() = 1.0

        override fun invertedMass() = 1.0
    }

    private class Level2(val children: List<ForceApplicable>) : ForceApplicables {
        private val id = ID++

        override fun toString() = "${javaClass.name}#$id"

        override fun children() = children

        override fun velocity() = Vector2d()

        override fun position() = Point2d()

        override fun mass() = 1.0

        override fun invertedMass() = 1.0

        override fun radian() = 1.0
    }

    private class Level3(val children: List<ForceApplicable>) : ForceApplicables {
        private val id = ID++

        override fun toString() = "${javaClass.name}#$id"

        override fun children() = children

        override fun velocity() = Vector2d()

        override fun position() = Point2d()

        override fun mass() = 1.0

        override fun invertedMass() = 1.0

        override fun radian() = 1.0
    }
}

private var ID = 1