package sergeygubar.github.io.currencyconverter.entities

data class ExchangeRate(val assetBase: Asset,
                        val assetQuote: Asset,
                        val rate: Double,
                        val time: String)