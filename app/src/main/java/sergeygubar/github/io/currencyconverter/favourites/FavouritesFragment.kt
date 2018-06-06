package sergeygubar.github.io.currencyconverter.favourites

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_favourites.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast
import sergeygubar.github.io.currencyconverter.R
import sergeygubar.github.io.currencyconverter.entities.FavouriteCurrency
import sergeygubar.github.io.currencyconverter.favourites.add.AddFavouriteActivity
import sergeygubar.github.io.currencyconverter.helpers.RandomHelper

private const val NEW_DATA_REQUEST_CODE = 500
const val FAVOURITE_FROM_KEY_EXTRA = "FAVOURITE_NAME"
const val FAVOURITE_TO_KEY_EXTRA = "FAVOURITE_TO"

class FavouritesFragment : Fragment(), AnkoLogger {

    private val favouritesOnlineRepository by lazy {
        FavouritesOnlineRepository(context!!)
    }

    companion object {
        fun newInstance(): Fragment {
            return FavouritesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? = inflater.inflate(R.layout.fragment_favourites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isOnline()) {
            favouritesOnlineRepository.getFavourites()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        val adapter = FavouritesAdapter(it.toMutableList())
                        favourites_recycler.adapter = adapter
                        favourites_recycler.layoutManager = LinearLayoutManager(context!!)
                    }
        } else {
            toast("Connection is unavailable. Loading Db data")
            favouritesOnlineRepository.getFavourites()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        val adapter = FavouritesAdapter(it.toMutableList())
                        favourites_recycler.adapter = adapter
                        favourites_recycler.layoutManager = LinearLayoutManager(context!!)
                    }
        }

        add_favourite_fab.setOnClickListener {
            startActivityForResult(intentFor<AddFavouriteActivity>(),
                    NEW_DATA_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.apply {
                val to = getStringExtra(FAVOURITE_TO_KEY_EXTRA)
                val from = getStringExtra(FAVOURITE_FROM_KEY_EXTRA)
                val pair = RandomHelper.nextRandom()
                (favourites_recycler.adapter as FavouritesAdapter).addData(FavouriteCurrency(
                        from,
                        to,
                        pair.first.toString().take(4),
                        pair.second.toString().take(4).toDouble(),
                        true
                ))
            }
        }
    }

    private fun isOnline(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

}

