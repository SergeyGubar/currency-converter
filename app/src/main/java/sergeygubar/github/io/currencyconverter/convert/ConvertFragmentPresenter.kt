package sergeygubar.github.io.currencyconverter.convert

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import sergeygubar.github.io.currencyconverter.helpers.ConvertHelper

class ConvertFragmentPresenter(private val view: ConvertFragmentView,
                               private val assetRepository: AssetRepository) : AnkoLogger {
    fun loadAssets() {
        info("load assets")
        val assets = assetRepository.loadAssets()
        assets.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    info("loadAssets = [$it]")
                    view.displayAssets(it)
                }, {
                    throw it
                })
    }

    fun convertCurrency(from: String, fromValue: Int, to: String) {
        info("convertCurrency from = [$from] to = [$to]")
        assetRepository.loadExchangeRate(from, to)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map { it.exchangeRate }
                .subscribe({ exchangeRate ->
                    info("convertCurrency = [${exchangeRate.rate}]")
                    val result = ConvertHelper.convert(fromValue, exchangeRate.rate)
                    view.displayConvertResult("$fromValue $from = $result $to.\n" +
                            "Time: ${exchangeRate.time}")
                }, {
                    throw it
                })
    }
}