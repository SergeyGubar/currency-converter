package sergeygubar.github.io.currencyconverter.settings

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import retrofit2.http.Query
import sergeygubar.github.io.currencyconverter.constants.BASE_AUTH_URL
import sergeygubar.github.io.currencyconverter.entities.RegisterResponse
import sergeygubar.github.io.currencyconverter.registration.RegisterApi

object UnregistrationManager {

    fun unregister(token: String,
                   userName: String,
                   password: String): Observable<RegisterResponse> {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_AUTH_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val registerApi = retrofit.create(UnregisterApi::class.java)
        return registerApi.unregister(token, userName, password)
    }
}