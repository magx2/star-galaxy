package star.galaxy.engine.entites

import org.springframework.util.Assert.hasText
import star.galaxy.engine.types.WithUuid

class IdentifiableObject(private val uuid: String,
                         val name: String) : WithUuid {

    init {
        hasText(uuid, "uuid")
        hasText(name, "name")
    }

    override fun uuid() = uuid
}