package star.galaxy.engine

import star.galaxy.engine.metainformations.Clearable
import star.galaxy.engine.types.ForceApplicable
import javax.vecmath.Vector2d

interface ForceService : Clearable {
    operator fun get(obj: ForceApplicable): Vector2d
}