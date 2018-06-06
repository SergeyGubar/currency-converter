package sergeygubar.github.io.currencyconverter.entities

import android.arch.persistence.room.Entity
import java.util.*

@Entity
data class FavouriteCurrency(var from: String,
                             var to: String,
                             var rate: String,
                             var change: Double,
                             var isUp: Boolean,
                             var id: String = UUID.randomUUID().toString()) {
    constructor(): this("","","", 0.0, true)
}