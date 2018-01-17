package star.galaxy.engine.types

import star.galaxy.engine.metainformations.Gram
import star.galaxy.engine.metainformations.GramInverted
import star.galaxy.engine.metainformations.Kilo
import star.galaxy.engine.metainformations.Milli

interface WithMass {
    @Kilo
    @Gram
    fun mass(): Double

    @Milli
    @GramInverted
    fun invertedMass(): Double
}