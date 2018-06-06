package sergeygubar.github.io.currencyconverter.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import sergeygubar.github.io.currencyconverter.constants.RATE_TABLE_NAME
import java.util.*

@Entity(tableName = RATE_TABLE_NAME)
data class ExchangeRate(
        var assetBaseId: String,
        var assetQuoteId: String,
        var rate: Double,
        var time: String,
        @PrimaryKey var id: String = UUID.randomUUID().toString()
        ) {

    constructor() : this("",", ", 0.0, "", UUID.randomUUID().toString())

}