package sergeygubar.github.io.currencyconverter.convert

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sergeygubar.github.io.currencyconverter.constants.BASE_URL
import sergeygubar.github.io.currencyconverter.entities.Asset
import sergeygubar.github.io.currencyconverter.entities.ExchangePojo


class OnlineAssetRepository : AssetRepository {

    // TODO : Remove multiple retrofit instantiation

    override fun loadAssets(): Observable<List<Asset>> {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val assetApi = retrofit.create(AssetApi::class.java)
        return assetApi.loadAssets()
    }

    override fun loadExchangeRate(from: String, to: String): Observable<ExchangePojo> {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val exchangeApi = retrofit.create(AssetApi::class.java)
        return exchangeApi.loadExchangeRate(from, to)
    }

}