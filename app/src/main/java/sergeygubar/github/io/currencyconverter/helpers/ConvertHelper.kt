package sergeygubar.github.io.currencyconverter.helpers

import org.jetbrains.anko.AnkoLogger

object ConvertHelper : AnkoLogger {
    fun convert(fromValue: Int, rate: Double): Double {
        return fromValue.toDouble() / rate
    }
}