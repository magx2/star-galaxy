package star.galaxy.engine.algebra

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import star.galaxy.engine.metainformations.Direction.CLOCKWISE
import star.galaxy.engine.metainformations.Direction.COUNTER_CLOCKWISE
import javax.vecmath.Vector2d

class AlgebraUtilsKtTest {
    @Test
    fun `should create perpendicular in clockwise direction`() {

        // given
        val vector = Vector2d(5.0, -3.0)

        // when
        val perpendicular = perpendicular(vector, CLOCKWISE)

        // then
        assertThat(perpendicular.x).`as`(perpendicular.toString()).isEqualTo(-3.0)
        assertThat(perpendicular.y).`as`(perpendicular.toString()).isEqualTo(-5.0)
    }

    @Test
    fun `should create perpendicular in counter clockwise direction`() {

        // given
        val vector = Vector2d(5.0, -3.0)

        // when
        val perpendicular = perpendicular(vector, COUNTER_CLOCKWISE)

        // then
        assertThat(perpendicular.x).`as`(perpendicular.toString()).isEqualTo(3.0)
        assertThat(perpendicular.y).`as`(perpendicular.toString()).isEqualTo(5.0)
    }
}