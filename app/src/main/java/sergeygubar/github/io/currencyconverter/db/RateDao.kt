package sergeygubar.github.io.currencyconverter.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Observable
import sergeygubar.github.io.currencyconverter.constants.ASSETS_TABLE_NAME
import sergeygubar.github.io.currencyconverter.constants.RATE_TABLE_NAME
import sergeygubar.github.io.currencyconverter.entities.Asset
import sergeygubar.github.io.currencyconverter.entities.ExchangeRate

@Dao
interface RateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(asset: ExchangeRate)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(assets: List<ExchangeRate>)

    @Query("SELECT * from $RATE_TABLE_NAME")
    fun getAll(): List<ExchangeRate>

    @Query("SELECT * from $RATE_TABLE_NAME WHERE to = :to AND from = :from")
    fun getRate(from: String, to: String): ExchangeRate}