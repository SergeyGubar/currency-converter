package sergeygubar.github.io.currencyconverter.convert

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import sergeygubar.github.io.currencyconverter.entities.Asset
import sergeygubar.github.io.currencyconverter.entities.ExchangePojo

interface AssetApi {
    @GET("assets")
    fun loadAssets(): Observable<List<Asset>>

    @GET("exchangerate/{from}/{to}")
    fun loadExchangeRate(@Path("from") from: String, @Path("to") to: String): Observable<ExchangePojo>

}