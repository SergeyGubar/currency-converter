package sergeygubar.github.io.currencyconverter.convert

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.info
import sergeygubar.github.io.currencyconverter.constants.TOKEN_KEY
import sergeygubar.github.io.currencyconverter.db.AssetsDataBase
import sergeygubar.github.io.currencyconverter.entities.Asset
import sergeygubar.github.io.currencyconverter.entities.ExchangeRate
import sergeygubar.github.io.currencyconverter.helpers.ConvertHelper
import kotlin.concurrent.thread

class ConvertFragmentPresenter(private val context: Context,
                               private val view: ConvertFragmentView,
                               private val assetRepository: AssetRepository) : AnkoLogger {

    private val token by lazy {
        context.defaultSharedPreferences.getString(TOKEN_KEY, "")
    }

    fun loadAssets() {
        info("load assets")
        val assets = assetRepository.loadAssets("Bearer $token")
        assets.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    info("loadAssets = [$it]")
                    view.displayAssets(it)
                    saveAssetsToDb(it)
                }, {
                    throw it
                })
    }

    fun convertCurrency(from: String, fromValue: Int, to: String) {
        info("convertCurrency from = [$from] to = [$to]")
        assetRepository.loadExchangeRate("Bearer $token", from, to)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .filter { it.message.toLowerCase() == "success" }
                .map { it.exchangeRate }
                .subscribe({ exchangeRate ->
                    exchangeRate.assetBaseId = from
                    exchangeRate.assetQuoteId = to
                    saveRateToDb(exchangeRate)
                    info("convertCurrency = [${exchangeRate.rate}]")
                    val result = ConvertHelper.convert(fromValue, exchangeRate.rate)
                    view.displayConvertResult("$fromValue $from = $result $to.\n" +
                            "Time: ${exchangeRate.time}")
                }, {
                    throw it
                })
    }

    private fun saveAssetsToDb(assets: List<Asset>) {
        thread(start = true) {
            AssetsDataBase.getInstance(context)!!.assetsDao().insertAll(assets)
        }
    }

    private fun saveRateToDb(rate: ExchangeRate) {
        thread(start = true) {
            AssetsDataBase.getInstance(context)!!.rateDao().insert(rate)
        }
    }
}