package sergeygubar.github.io.currencyconverter.favourites.add

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_favourite.*
import sergeygubar.github.io.currencyconverter.R
import sergeygubar.github.io.currencyconverter.convert.OfflineAssetRepository
import sergeygubar.github.io.currencyconverter.entities.Asset
import sergeygubar.github.io.currencyconverter.favourites.FAVOURITE_FROM_KEY_EXTRA
import sergeygubar.github.io.currencyconverter.favourites.FAVOURITE_TO_KEY_EXTRA

class AddFavouriteActivity : AppCompatActivity() {

    private val assetIds = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_favourite)
        val assetRepository = OfflineAssetRepository(this)
        assetRepository.loadAssets("")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ initSpinners(it) })

        save_button.setOnClickListener {
            val intent = Intent()
            intent.putExtra(FAVOURITE_FROM_KEY_EXTRA, assetIds[from_spinner.selectedIndex])
            intent.putExtra(FAVOURITE_TO_KEY_EXTRA, assetIds[to_spinner.selectedIndex])
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }

    private fun initSpinners(data: List<Asset>) {
        val ids = data.map {
            it.assetId
        }
        assetIds.addAll(ids)
        from_spinner.setItems(ids)
        to_spinner.setItems(ids)


    }
}
