package sergeygubar.github.io.currencyconverter.convert

import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import sergeygubar.github.io.currencyconverter.db.AssetsDataBase
import sergeygubar.github.io.currencyconverter.entities.Asset
import sergeygubar.github.io.currencyconverter.entities.ExchangePojo
import sergeygubar.github.io.currencyconverter.entities.ExchangeRate

class OfflineAssetRepository(private val context: Context) : AssetRepository, AnkoLogger {

    override fun loadAssets(token: String): Observable<List<Asset>> {
        return Observable.fromCallable {
            AssetsDataBase.getInstance(context)!!.assetsDao().getAll()
        }
    }

    override fun loadExchangeRate(token: String, from: String, to: String): Observable<ExchangePojo> {
        return Observable.fromCallable {
            info( "loadExchangeRate ${AssetsDataBase.getInstance(context)!!.rateDao().getAll()}" )
            info("from = [$from] to = [$to]")
            try {
                // I'm very, very sorry - i've done it in the last day
                ExchangePojo("success", AssetsDataBase.getInstance(context)!!.rateDao().getRate(from, to)!!)
            } catch (ex: KotlinNullPointerException) {
                ExchangePojo("fail", ExchangeRate())
            }
        }
    }
}