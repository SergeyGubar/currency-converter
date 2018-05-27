package sergeygubar.github.io.currencyconverter.convert

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import sergeygubar.github.io.currencyconverter.R

class ConvertFragment : Fragment(), AnkoLogger {

    companion object {
        fun newInstance(): Fragment {
            return ConvertFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_convert, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}