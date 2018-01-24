package star.galaxy.engine.utils

import java.util.stream.Collectors

class MultiList<T>(private val lists: List<List<T>>) : List<T> {
    constructor(vararg lists: List<T>) : this(listOf(*lists))

    override val size: Int
        get() = lists.stream()
                .mapToInt { it.size }
                .sum()

    override fun contains(element: T) = lists.stream().filter({ it.contains(element) }).findAny().isPresent

    override fun containsAll(elements: Collection<T>) =
            elements.stream()
                    .map { element -> contains(element) }
                    .filter { !it }
                    .findAny()
                    .isPresent
                    .not()

    override fun get(index: Int): T =
            lists.stream()
                    .flatMap { it.stream() }
                    .skip(index.toLong())
                    .findFirst()
                    .orElseThrow { throw IndexOutOfBoundsException(index) }

    override fun indexOf(element: T): Int {
        var idx = 0
        lists.forEach { list ->
            list.forEach { item ->
                if (item == element) {
                    return idx
                } else {
                    idx++
                }
            }
        }
        return -1
    }

    override fun isEmpty() = size == 0

    override fun iterator(): Iterator<T> = MultiListIterator()

    override fun lastIndexOf(element: T): Int {
        var idx = size - 1
        for (i in (lists.size - 1) downTo 0) {
            val list = lists[i]
            for (j in (list.size - 1) downTo 0) {
                val item = list[j]
                if (item == element) {
                    return idx
                } else {
                    idx--
                }
            }
        }
        return -1
    }

    override fun listIterator() =
            lists.stream()
                    .flatMap { it.stream() }
                    .collect(Collectors.toList())
                    .listIterator()

    override fun listIterator(index: Int) =
            lists.stream()
                    .flatMap { it.stream() }
                    .collect(Collectors.toList())
                    .listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): List<T> =
            lists.stream()
                    .flatMap { it.stream() }
                    .collect(Collectors.toList())
                    .subList(fromIndex, toIndex)

    private inner class MultiListIterator : Iterator<T> {
        private val iterators: Iterator<Iterator<T>> = lists.stream().map { it.iterator() }.collect(Collectors.toList()).iterator()
        private var iterator: Iterator<T>? = null

        override fun hasNext(): Boolean {
            initIterator()
            return iterator?.hasNext() ?: false
        }

        override fun next(): T {
            return iterator!!.next()
        }

        private fun initIterator() {
            if (iterator == null || !iterator!!.hasNext()) {
                if (iterators.hasNext()) {
                    iterator = iterators.next()
                }
            }
        }
    }
}