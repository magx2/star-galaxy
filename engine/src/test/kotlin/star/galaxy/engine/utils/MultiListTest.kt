package star.galaxy.engine.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.util.stream.Stream.concat

class MultiListTest {
    val list1 = listOf("list1.1")
    val list2 = listOf("list2.1", "list2.2")
    val list3 = listOf("list3.1", "list3.2", "list3.3")

    lateinit var multiList: List<String>

    @Before
    fun setUp() {
        multiList = MultiList(list1, list2, list3)
    }

    @Test
    fun `size`() {
        assertThat(multiList.size).isEqualTo(6)
    }

    @Test
    fun `contains`() {
        concat(concat(list1.stream(), list2.stream()), list3.stream()).forEach { element ->
            assertThat(multiList.contains(element))
                    .`as`(element.toString())
                    .isTrue()
        }
    }

    @Test
    fun `containsAll`() {
        assertThat(multiList.containsAll(list1)).isTrue()
        assertThat(multiList.containsAll(list2)).isTrue()
        assertThat(multiList.containsAll(list3)).isTrue()
    }

    @Test
    fun `get first`() {
        assertThat(multiList[0]).isEqualTo("list1.1")
    }

    @Test
    fun `get last`() {
        assertThat(multiList[multiList.size - 1]).isEqualTo("list3.3")
    }

    @Test
    fun `indexOf first`() {
        assertThat(multiList.indexOf("list1.1")).isEqualTo(0)
    }

    @Test
    fun `indexOf last`() {
        assertThat(multiList.indexOf("list3.3")).isEqualTo(multiList.size - 1)
    }

    @Test
    fun `indexOf none`() {
        assertThat(multiList.indexOf("foo")).isEqualTo(-1)
    }

    @Test
    fun `isEmpty false`() {
        assertThat(multiList.isEmpty()).isFalse()
    }

    @Test
    fun `isEmpty true`() {
        assertThat(MultiList<Any>(listOf()).isEmpty()).isTrue()
    }

    @Test
    fun `subList`() {
        assertThat(multiList.subList(1, 5)).containsExactlyElementsOf(listOf("list2.1", "list2.2", "list3.1", "list3.2"))
    }

    @Test
    fun `should iterate over all elements`() {

        // given
        val allList = ArrayList<String>()
        allList.addAll(list1)
        allList.addAll(list2)
        allList.addAll(list3)


        // when
        val it = multiList.iterator()
        val list = ArrayList<String>()
        while (it.hasNext()) {
            list.add(it.next())
        }

        // then
        assertThat(list).containsExactlyElementsOf(allList)
        assertThat(list).hasSize(allList.size)
    }

    @Test
    fun `should iterate over all with list iterator`() {

        // given
        val allList = ArrayList<String>()
        allList.addAll(list1)
        allList.addAll(list2)
        allList.addAll(list3)


        // when
        val it = multiList.listIterator()
        val list = ArrayList<String>()
        while (it.hasNext()) {
            list.add(it.next())
        }

        // then
        assertThat(list).containsExactlyElementsOf(allList)
        assertThat(list).hasSize(allList.size)
    }

    @Test
    fun `should iterate from idx=1 element with list iterator`() {

        // given
        val allList = ArrayList<String>()
        allList.addAll(list2)
        allList.addAll(list3)


        // when
        val it = multiList.listIterator(1)
        val list = ArrayList<String>()
        while (it.hasNext()) {
            list.add(it.next())
        }

        // then
        assertThat(list).containsExactlyElementsOf(allList)
        assertThat(list).hasSize(allList.size)
    }

    @Test
    fun `lastIndexOf first`() {

        // given
        multiList = MultiList(list1, list2, list3, list1, list2, list3)

        // when
        val lastIndexOf = multiList.lastIndexOf("list1.1")

        assertThat(lastIndexOf).isEqualTo(6)
    }

    @Test
    fun `lastIndexOf last`() {

        // given
        multiList = MultiList(list1, list2, list3, list1, list2, list3)

        // when
        val lastIndexOf = multiList.lastIndexOf("list3.3")

        assertThat(lastIndexOf).isEqualTo(11)
    }

    @Test
    fun `lastIndexOf none`() {

        // given
        multiList = MultiList(list1, list2, list3, list1, list2, list3)

        // when
        val lastIndexOf = multiList.lastIndexOf("foo")

        assertThat(lastIndexOf).isEqualTo(-1)
    }
}
