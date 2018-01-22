package star.galaxy.engine.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MultiSetTest {

    @Test
    fun `should return size of all elements sum up`() {

        // given
        val set1 = setOf("set1")
        val set2 = setOf("set2.1", "set2.2")
        val set3 = setOf("set3.1", "set3.2", "set3.3")

        val multiSet = MultiSet(set1, set2, set3)

        // when
        val size = multiSet.size

        // then
        assertThat(size).isEqualTo(1 + 2 + 3)
    }

    @Test
    fun `should find element in first set`() {

        // given
        val set1 = setOf("set1")
        val set2 = setOf("set2.1", "set2.2")
        val set3 = setOf("set3.1", "set3.2", "set3.3")

        val multiSet = MultiSet(set1, set2, set3)

        // when
        val contains = multiSet.contains("set1")

        // then
        assertThat(contains).isTrue()
    }

    @Test
    fun `should find element in last set`() {

        // given
        val set1 = setOf("set1")
        val set2 = setOf("set2.1", "set2.2")
        val set3 = setOf("set3.1", "set3.2", "set3.3")

        val multiSet = MultiSet(set1, set2, set3)

        // when
        val contains = multiSet.contains("set3.2")

        // then
        assertThat(contains).isTrue()
    }

    @Test
    fun `should find element in last set - after addition`() {

        // given
        val set1 = setOf("set1")
        val set2 = setOf("set2.1", "set2.2")
        val set3 = setOf("set3.1", "set3.2", "set3.3").toMutableSet()

        val multiSet = MultiSet(set1, set2, set3)

        val element = "foo"
        set3.add(element)

        // when
        val contains = multiSet.contains(element)

        // then
        assertThat(contains).isTrue()
    }

    @Test
    fun `should not find element`() {

        // given
        val set1 = setOf("set1")
        val set2 = setOf("set2.1", "set2.2")
        val set3 = setOf("set3.1", "set3.2", "set3.3")

        val multiSet = MultiSet(set1, set2, set3)

        // when
        val contains = multiSet.contains("boo")

        // then
        assertThat(contains).isFalse()
    }

    @Test
    fun `should find elements from all sets`() {

        // given
        val set1 = setOf("set1")
        val set2 = setOf("set2.1", "set2.2")
        val set3 = setOf("set3.1", "set3.2", "set3.3")

        val multiSet = MultiSet(set1, set2, set3)

        // when
        val contains = multiSet.containsAll(listOf("set1", "set2.2", "set3.1"))

        // then
        assertThat(contains).isTrue()
    }

    @Test
    fun `should not find elements if one is not matching`() {

        // given
        val set1 = setOf("set1")
        val set2 = setOf("set2.1", "set2.2")
        val set3 = setOf("set3.1", "set3.2", "set3.3")

        val multiSet = MultiSet(set1, set2, set3)

        // when
        val contains = multiSet.containsAll(listOf("set1", "set2.2", "set3.1", "boo"))

        // then
        assertThat(contains).isFalse()
    }

    @Test
    fun `should return false if one of sets is not empty`() {

        // given
        val set1 = setOf("set1")
        val set2 = setOf<String>()
        val set3 = setOf<String>()

        val multiSet = MultiSet(set1, set2, set3)

        // when
        val empty = multiSet.isEmpty()

        // then
        assertThat(empty).isFalse()
    }

    @Test
    fun `should return true if all of sets are empty`() {

        // given
        val set1 = setOf<String>()
        val set2 = setOf<String>()
        val set3 = setOf<String>()

        val multiSet = MultiSet(set1, set2, set3)

        // when
        val empty = multiSet.isEmpty()

        // then
        assertThat(empty).isTrue()
    }


    @Test
    fun `should iterate over all elements`() {

        // given
        val set1 = setOf("set1")
        val set2 = setOf("set2.1", "set2.2")
        val set3 = setOf("set3.1", "set3.2", "set3.3")
        val allSet = HashSet<String>()
        allSet.addAll(set1)
        allSet.addAll(set2)
        allSet.addAll(set3)

        val multiSet = MultiSet(set1, set2, set3)

        // when
        val it = multiSet.iterator()
        val set = HashSet<String>()
        while (it.hasNext()) {
            set.add(it.next())
        }

        // then
        assertThat(set).containsAll(allSet)
        assertThat(set).hasSize(allSet.size)
    }
}