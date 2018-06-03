package sergeygubar.github.io.currencyconverter.db

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import sergeygubar.github.io.currencyconverter.constants.DATABASE_NAME

abstract class AssetsDataBase : RoomDatabase() {

    abstract fun assetsDao(): AssetsDao

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