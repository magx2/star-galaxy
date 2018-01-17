package star.galaxy.engine.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.Universe
import star.galaxy.engine.UniverseConstants
import star.galaxy.engine.types.ForceApplicable
import star.galaxy.engine.types.WithGravity

@Service
class BasicUniverse : Universe {
    override fun universeConstants() = UniverseConstants()

    override fun withGravity(): Set<WithGravity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun forceApplicable(): Set<ForceApplicable> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}