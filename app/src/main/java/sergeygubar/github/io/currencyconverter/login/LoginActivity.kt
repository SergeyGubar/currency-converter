package sergeygubar.github.io.currencyconverter.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import sergeygubar.github.io.currencyconverter.R


private const val SERVER_CLIENT_ID = "918564697902-7qscjgn9ldnioinclim0qtul4ad66p0p.apps.googleusercontent.com"

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {


    private lateinit var mGoogleApiClient: GoogleApiClient
    private val OUR_REQUEST_CODE = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(SERVER_CLIENT_ID)
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        val opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient)

        if (opr.isDone) {
            toast("User is logged in")
        } else {
            toast("User is not logged in")
        }

        log_in_button.setOnClickListener {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
            startActivityForResult(signInIntent, OUR_REQUEST_CODE)
        }


    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResult(requestCode: Int, responseCode: Int,
                                  intent: Intent) {
        super.onActivityResult(requestCode, responseCode, intent)

        if (requestCode == OUR_REQUEST_CODE) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(intent)
            handleSignInResult(result)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
            val acct = result.signInAccount
            // If you don't already have a server session, you can now send this code to your
            // server to authenticate on the backend.
            val authCode = acct!!.serverAuthCode
            toast(authCode!!)

        }
    }
}
