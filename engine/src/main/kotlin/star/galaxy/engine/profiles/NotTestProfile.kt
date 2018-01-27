package star.galaxy.engine.profiles

import org.springframework.context.annotation.Profile

@Profile("!test")
annotation class NotTestProfile