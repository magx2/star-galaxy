package star.galaxy.engine.utils

import java.util.stream.Stream

fun <T, Y : T> Stream<T>.castTo(clazz: Class<Y>): Stream<Y> =
        this.filter(clazz::isInstance)
                .map(clazz::cast)