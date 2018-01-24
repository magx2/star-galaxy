package star.galaxy.engine.utils

import java.util.stream.Collectors

class MultiSet<T>(private val sets: Set<Set<T>>) : Set<T> {
    constructor(vararg sets: Set<T>) : this(setOf(*sets))

    override val size = sets.stream().mapToInt({ it.size }).sum()

    override fun contains(element: T) = sets.stream().filter({ it.contains(element) }).findAny().isPresent

    override fun containsAll(elements: Collection<T>) =
            elements.stream()
                    .map { element -> contains(element) }
                    .filter { !it }
                    .findAny()
                    .isPresent
                    .not()

    override fun isEmpty() = size == 0

    override fun iterator(): Iterator<T> = MultiSetIterator()

    private inner class MultiSetIterator : Iterator<T> {
        private val iterators: Iterator<Iterator<T>> = sets.stream().map { it.iterator() }.collect(Collectors.toSet()).toSet().iterator()
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
