package sergeygubar.github.io.currencyconverter.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import sergeygubar.github.io.currencyconverter.constants.ASSETS_ID
import sergeygubar.github.io.currencyconverter.constants.ASSETS_IS_CRYPTO
import sergeygubar.github.io.currencyconverter.constants.ASSETS_NAME
import sergeygubar.github.io.currencyconverter.constants.ASSETS_TABLE_NAME
import java.util.*

@Entity(tableName = ASSETS_TABLE_NAME)
data class Asset(@PrimaryKey var id: String = UUID.randomUUID().toString(),
                 @ColumnInfo(name = ASSETS_ID) var assetId: String,
                 @ColumnInfo(name = ASSETS_NAME) var name: String,
                 @ColumnInfo(name = ASSETS_IS_CRYPTO) var isTypeCrypto: Boolean) {
    // Room constructor
    constructor() : this(UUID.randomUUID().toString(), "", "", false)

}