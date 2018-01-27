package star.galaxy.engine

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import star.galaxy.engine.profiles.NotTestProfile

@NotTestProfile
@Component
internal class Orchestrator(private val engine: Engine,
                            private val clock: Clock) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        Thread({
            while (true) {
                engine.nextStep(clock.next())
            }
            // can add notification that engine is down
        }, "PHYSICS-ENGINE").start()
    }
}