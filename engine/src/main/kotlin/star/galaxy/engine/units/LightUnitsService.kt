package star.galaxy.engine.units

import star.galaxy.engine.metainformations.*

interface LightUnitsService {
    @Meter
    fun lightYearsToMeters(@LightYears value: Double): Double

    @Meter
    fun lightDaysToMeters(@LightDays value: Double): Double

    @Meter
    fun lightHoursToMeters(@LightHours value: Double): Double

    @Meter
    fun lightMinutesToMeters(@LightMinutes value: Double): Double

    @Meter
    fun lightSecondsToMeters(@LightSeconds value: Double): Double
}