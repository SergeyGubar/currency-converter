package sergeygubar.github.io.currencyconverter.convert

import io.reactivex.Observable
import retrofit2.http.GET
import sergeygubar.github.io.currencyconverter.entities.Asset

interface AssetApi {
    @GET("assets")
    fun loadAssets(): Observable<List<Asset>>
}