package sergeygubar.github.io.currencyconverter.login

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import sergeygubar.github.io.currencyconverter.entities.LoginResponse

interface LoginApi {

    @GET("auth/createToken")
    fun login(@Query("UserName") username: String,
              @Query("Password") password: String): Observable<LoginResponse>

}