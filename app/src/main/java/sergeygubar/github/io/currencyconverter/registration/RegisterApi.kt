package sergeygubar.github.io.currencyconverter.registration

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import sergeygubar.github.io.currencyconverter.entities.RegisterResponse

interface RegisterApi {
    @GET("/register/register")
    fun register(@Query("UserName") userName: String,
                 @Query("Password") password: String): Observable<RegisterResponse>
}