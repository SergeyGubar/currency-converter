package sergeygubar.github.io.currencyconverter.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import sergeygubar.github.io.currencyconverter.constants.ASSETS_TABLE_NAME
import sergeygubar.github.io.currencyconverter.constants.RATE_TABLE_NAME
import java.util.*

@Entity(tableName = RATE_TABLE_NAME)
data class ExchangeRate(
        val assetBase: Asset,
        val assetQuote: Asset,
        val rate: Double,
        val time: String,
        @PrimaryKey var id: String = UUID.randomUUID().toString(),
        var from: String = assetBase.assetId,
        var to: String = assetQuote.assetId) {

    constructor() : this(Asset(), Asset(), 0.0, "", UUID.randomUUID().toString())

}