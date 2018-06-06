package sergeygubar.github.io.currencyconverter.helpers

import java.util.*

object RandomHelper {
    fun nextRandom(): Pair<Double, Double> {
        val data = Random().nextDouble()
        return (data to data / 50)
    }
}