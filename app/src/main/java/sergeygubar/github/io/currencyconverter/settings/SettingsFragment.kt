package sergeygubar.github.io.currencyconverter.settings

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_settings.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.defaultSharedPreferences
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast
import sergeygubar.github.io.currencyconverter.R
import sergeygubar.github.io.currencyconverter.constants.EMAIL_KEY
import sergeygubar.github.io.currencyconverter.constants.PASSWORD_KEY
import sergeygubar.github.io.currencyconverter.constants.TOKEN_KEY
import sergeygubar.github.io.currencyconverter.login.LoginActivity

class SettingsFragment : Fragment(), AnkoLogger {

    private val token by lazy {
        defaultSharedPreferences.getString(TOKEN_KEY, "")
    }

    private val email by lazy {
        defaultSharedPreferences.getString(EMAIL_KEY, "")
    }

    private val password by lazy {
        defaultSharedPreferences.getString(PASSWORD_KEY, "")
    }

    companion object {
        fun newInstance(): Fragment {
            return SettingsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? = inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unregister_button.setOnClickListener {
            UnregistrationManager.unregister(token, email, password)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        defaultSharedPreferences.edit().putString(TOKEN_KEY, "").apply()
                        activity?.finish()
                        startActivity(intentFor<LoginActivity>())
                    }, { toast("You are not connected!") })
        }

        log_out_button.setOnClickListener {
            defaultSharedPreferences.edit().putString(TOKEN_KEY, "").apply()
            activity?.finish()
            startActivity(intentFor<LoginActivity>())
        }
    }
}