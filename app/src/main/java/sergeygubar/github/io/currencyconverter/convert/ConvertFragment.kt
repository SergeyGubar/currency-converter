package sergeygubar.github.io.currencyconverter.convert

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.toast
import sergeygubar.github.io.currencyconverter.R
import sergeygubar.github.io.currencyconverter.entities.Asset

class ConvertFragment : Fragment(), AnkoLogger, ConvertFragmentView {


    private lateinit var presenter: ConvertFragmentPresenter

    companion object {
        fun newInstance(): Fragment {
            return ConvertFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_convert, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ConvertFragmentPresenter(this, OnlineAssetRepository())
        presenter.loadAssets()
    }

    override fun displayAssets(assets: List<Asset>) {
        toast("assets loaded!")
        // TODO
    }
}