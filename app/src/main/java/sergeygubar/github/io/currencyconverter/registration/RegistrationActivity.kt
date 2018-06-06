package sergeygubar.github.io.currencyconverter.registration

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_registration.*
import org.jetbrains.anko.*
import sergeygubar.github.io.currencyconverter.R
import sergeygubar.github.io.currencyconverter.constants.EMAIL_KEY
import sergeygubar.github.io.currencyconverter.constants.PASSWORD_KEY
import sergeygubar.github.io.currencyconverter.constants.TOKEN_KEY
import sergeygubar.github.io.currencyconverter.login.OnlineLoginRepository
import sergeygubar.github.io.currencyconverter.main.MainActivity

class RegistrationActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        register_button.setOnClickListener {
            registerUser(email_edit_text.text.toString(),
                    password_edit_text.text.toString())
        }
    }

    private fun registerUser(email: String, password: String) {
        info("registerUser email = [$email] password = [$password]")
        RegistrationManager
                .sendRegistrationRequest(email, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    info("${it.isSuccess}")
                    loginUser(email, password)
                }, {
                    info("${it.cause}")
                    throw(it)
                })
    }

    private fun loginUser(email: String, password: String) {
        OnlineLoginRepository
                .sendLoginRequest(email, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    defaultSharedPreferences.edit()
                            .putString(TOKEN_KEY, it.token)
                            .putString(EMAIL_KEY, email)
                            .putString(PASSWORD_KEY, password)
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
