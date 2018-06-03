package sergeygubar.github.io.currencyconverter.convert

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ConvertFragmentPresenter(private val view: ConvertFragmentView,
                               private val assetRepository: AssetRepository) : AnkoLogger {
    fun loadAssets() {
        val assets = assetRepository.loadAssets()
        assets.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    info("assets = [$it]")
                    view.displayAssets(it)
                }, {
                    throw it
                })
    }
}