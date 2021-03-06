package sergeygubar.github.io.currencyconverter.convert

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_convert.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import sergeygubar.github.io.currencyconverter.R
import sergeygubar.github.io.currencyconverter.entities.Asset
import android.net.ConnectivityManager
import org.jetbrains.anko.support.v4.toast


class ConvertFragment : Fragment(), AnkoLogger, ConvertFragmentView {

    private lateinit var presenter: ConvertFragmentPresenter
    private val assetsIds: MutableList<String> = mutableListOf()

    companion object {
        fun newInstance(): Fragment {
            return ConvertFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? = inflater.inflate(R.layout.fragment_convert, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        convert_button.setOnClickListener {
            if (!isOnline()) {
                presenter = ConvertFragmentPresenter(context!!, this, OfflineAssetRepository(context!!))
                toast("Internet is currently unavailable. Loading db data")
            }
            presenter.convertCurrency(
                    assetsIds[currency_from_spinner.selectedIndex],
                    value_edit_text.text.toString().toInt(),
                    assetsIds[currency_to_spinner.selectedIndex]
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = ConvertFragmentPresenter(context!!, this, OnlineAssetRepository(context!!))
        presenter.loadAssets()
    }

    override fun displayAssets(assets: List<Asset>) {
        info("displayAssets assets = [$assets]")

        assetsIds.addAll(assets.map { it.assetId })

        currency_to_spinner.setItems(assetsIds)
        currency_from_spinner.setItems(assetsIds)
    }

    override fun displayConvertResult(result: String) {
        info("displayConvertResult result = [$result]")
        result_text_view.text = result
    }

    private fun isOnline(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}