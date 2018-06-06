package sergeygubar.github.io.currencyconverter.settings

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import sergeygubar.github.io.currencyconverter.entities.RegisterResponse

interface UnregisterApi {

    @GET("/register/unregister")
    fun unregister(@Header("Authorization") token: String,
                   @Query("UserName") userName: String,
                   @Query("Password") password: String): Observable<RegisterResponse>
}