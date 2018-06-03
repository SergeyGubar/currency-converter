package sergeygubar.github.io.currencyconverter.convert

import io.reactivex.Observable
import sergeygubar.github.io.currencyconverter.entities.Asset
import sergeygubar.github.io.currencyconverter.entities.ExchangePojo

interface AssetRepository {

    fun loadAssets(): Observable<List<Asset>>

    fun loadExchangeRate(from: String, to: String): Observable<ExchangePojo>

}