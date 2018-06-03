package sergeygubar.github.io.currencyconverter.convert

import sergeygubar.github.io.currencyconverter.entities.Asset

interface ConvertFragmentView {
    fun displayAssets(assets: List<Asset>)
}