package sergeygubar.github.io.currencyconverter.convert

import io.reactivex.Observable
import sergeygubar.github.io.currencyconverter.entities.Asset

interface AssetRepository {
    fun loadAssets(): Observable<List<Asset>>
}