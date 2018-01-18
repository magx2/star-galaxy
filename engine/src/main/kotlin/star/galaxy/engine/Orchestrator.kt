package star.galaxy.engine

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
internal class Orchestrator @Autowired constructor(private val engine: Engine,
                                                   private val universeLoader: UniverseLoader,
                                                   private val clock: Clock) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val universe = universeLoader.load()
        while (true) {
            engine.nextStep(universe, clock.next())
        }
    }
}