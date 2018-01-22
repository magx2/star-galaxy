package star.galaxy.engine.units.impl

import org.springframework.stereotype.Service
import star.galaxy.engine.units.LightUnitsService

@Service
internal class LightUnitsServiceImpl : LightUnitsService {
    override fun lightYearsToMeters(value: Double) = value * 9_460_730_472_580_800

    override fun lightDaysToMeters(value: Double): Double = value * 25_902_068_371_200

    override fun lightHoursToMeters(value: Double) = value * 1_079_252_848_800

    override fun lightMinutesToMeters(value: Double) = value * 17_987_547_480

    override fun lightSecondsToMeters(value: Double) = value * 299_792_458
}