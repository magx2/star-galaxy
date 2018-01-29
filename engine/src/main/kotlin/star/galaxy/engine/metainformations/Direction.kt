package star.galaxy.engine.metainformations

enum class Direction {
    CLOCKWISE, COUNTER_CLOCKWISE
}

fun findDirection(value: Double) =
        when {
            value > 0.0 -> Direction.CLOCKWISE
            value < 0.0 -> Direction.COUNTER_CLOCKWISE
            else -> throw IllegalArgumentException("Given value cannot be zero!")
        }
