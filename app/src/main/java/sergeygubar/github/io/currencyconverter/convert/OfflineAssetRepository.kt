package sergeygubar.github.io.currencyconverter.convert

import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sergeygubar.github.io.currencyconverter.db.AssetsDataBase
import sergeygubar.github.io.currencyconverter.entities.Asset
import sergeygubar.github.io.currencyconverter.entities.ExchangePojo

class OfflineAssetRepository(private val context: Context) : AssetRepository {

    override fun loadAssets(token: String): Observable<List<Asset>> {
        return Observable.fromCallable {
            AssetsDataBase.getInstance(context)!!.assetsDao().getAll()
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    override fun loadExchangeRate(token: String, from: String, to: String): Observable<ExchangePojo> {
        throw NotImplementedError()
    }
}