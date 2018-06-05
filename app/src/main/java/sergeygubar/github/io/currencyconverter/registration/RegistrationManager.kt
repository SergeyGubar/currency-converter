package sergeygubar.github.io.currencyconverter.registration

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sergeygubar.github.io.currencyconverter.constants.BASE_AUTH_URL
import sergeygubar.github.io.currencyconverter.entities.RegisterResponse

object RegistrationManager {
    fun sendRegistrationRequest(userName: String, password: String): Observable<RegisterResponse> {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_AUTH_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val registerApi = retrofit.create(RegisterApi::class.java)
        return registerApi.register(userName, password)
    }
}