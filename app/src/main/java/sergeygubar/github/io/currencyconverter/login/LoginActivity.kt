package sergeygubar.github.io.currencyconverter.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import sergeygubar.github.io.currencyconverter.R
import sergeygubar.github.io.currencyconverter.R.id.*
import sergeygubar.github.io.currencyconverter.constants.EMAIL_KEY
import sergeygubar.github.io.currencyconverter.constants.PASSWORD_KEY
import sergeygubar.github.io.currencyconverter.constants.TOKEN_KEY
import sergeygubar.github.io.currencyconverter.main.MainActivity
import sergeygubar.github.io.currencyconverter.registration.RegistrationActivity

private const val PASSWORD_PATTERN = "^[a-zA-Z0-9]+$"
private const val EMAIL_PATTERN = "^[A-Z0-9.]+@[A-Z0-9.]+\\.[A-Z]{2,}\$"


class LoginActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val token = defaultSharedPreferences.getString(TOKEN_KEY, "")

        if (token.isNotEmpty()) {
            startMainActivity()
        }

        log_in_button.setOnClickListener {
            login()
        }

        register_button.setOnClickListener {
            startActivity(intentFor<RegistrationActivity>())
        }
    }

    private fun login() {
        val email = email_edit_text.text.toString()
        val password = password_edit_text.text.toString()

        if (true) {
            OnlineLoginRepository.sendLoginRequest(email, password)
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
                        toast("There is no such user!")
                    })
        } else {
            toast("Incorrect email or password!")
        }
    }

    private fun startMainActivity() {
        startActivity(intentFor<MainActivity>())
        finish()
    }


}
