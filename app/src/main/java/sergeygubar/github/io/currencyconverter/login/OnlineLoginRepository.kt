package sergeygubar.github.io.currencyconverter.login

import io.reactivex.Observable
import org.jetbrains.anko.AnkoLogger
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sergeygubar.github.io.currencyconverter.constants.BASE_AUTH_URL
import sergeygubar.github.io.currencyconverter.constants.BASE_URL
import sergeygubar.github.io.currencyconverter.entities.LoginResponse

object OnlineLoginRepository: AnkoLogger {
    fun sendLoginRequest(userName: String, password: String): Observable<LoginResponse> {

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_AUTH_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val loginApi = retrofit.create(LoginApi::class.java)
        return loginApi.login(userName, password)
    }
}