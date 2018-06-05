package sergeygubar.github.io.currencyconverter.registration

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_registration.*
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import sergeygubar.github.io.currencyconverter.R
import sergeygubar.github.io.currencyconverter.constants.TOKEN_KEY
import sergeygubar.github.io.currencyconverter.login.OnlineLoginRepository
import sergeygubar.github.io.currencyconverter.main.MainActivity

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        register_button.setOnClickListener {
            registerUser(email_edit_text.text.toString(),
                    password_edit_text.text.toString())
        }
    }

    private fun registerUser(email: String, password: String) {
        RegistrationManager.sendRegistrationRequest(email, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    loginUser(email, password)
                }, {
                    throw(it)
                })
    }

    private fun loginUser(email: String, password: String) {
        OnlineLoginRepository.sendLoginRequest(email, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    defaultSharedPreferences.edit()
                            .putString(TOKEN_KEY, it.token)
                            .apply()
                    startMainActivity()
                }, {
                    throw(it)
                })
    }

    private fun startMainActivity() {
        startActivity(intentFor<MainActivity>())
        finish()
    }
}
