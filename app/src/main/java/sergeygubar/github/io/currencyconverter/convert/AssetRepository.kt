package sergeygubar.github.io.currencyconverter.convert

import io.reactivex.Observable
import sergeygubar.github.io.currencyconverter.entities.Asset
import sergeygubar.github.io.currencyconverter.entities.ExchangePojo

interface AssetRepository {

    fun loadAssets(token: String): Observable<List<Asset>>

    fun loadExchangeRate(token: String, from: String, to: String): Observable<ExchangePojo>

}