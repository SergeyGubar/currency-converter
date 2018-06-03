package sergeygubar.github.io.currencyconverter.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Observable
import sergeygubar.github.io.currencyconverter.constants.ASSETS_TABLE_NAME
import sergeygubar.github.io.currencyconverter.entities.Asset

@Dao
interface AssetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(asset: Asset)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(assets: List<Asset>)

    @Query("SELECT * from $ASSETS_TABLE_NAME")
    fun getAll(): Observable<List<Asset>>

}