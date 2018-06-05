package sergeygubar.github.io.currencyconverter.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import sergeygubar.github.io.currencyconverter.constants.DATABASE_NAME
import sergeygubar.github.io.currencyconverter.entities.Asset
import sergeygubar.github.io.currencyconverter.entities.ExchangeRate

@Database(entities = [Asset::class, ExchangeRate::class], version = 1)
abstract class AssetsDataBase : RoomDatabase() {

    abstract fun assetsDao(): AssetsDao

    abstract fun rateDao(): RateDao

    companion object {

        private var INSTANCE: AssetsDataBase? = null

        fun getInstance(context: Context?): AssetsDataBase? {
            if (INSTANCE == null) {
                synchronized(AssetsDataBase::class.java) {
                    INSTANCE = Room.databaseBuilder(context!!.applicationContext, AssetsDataBase::class.java,
                            DATABASE_NAME).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}