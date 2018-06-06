package sergeygubar.github.io.currencyconverter.favourites

import android.content.Context
import io.reactivex.Observable
import sergeygubar.github.io.currencyconverter.entities.FavouriteCurrency
import java.util.concurrent.TimeUnit

class FavouritesOnlineRepository(context: Context) {
    fun getFavourites(): Observable<List<FavouriteCurrency>> {
        return Observable.just(listOf(
                FavouriteCurrency("UAH", "USD", "26.180", 0.086, false),
                FavouriteCurrency("EUR", "USD", "1.170", 0.014, false),
                FavouriteCurrency("UAH", "EUR", "0.033", 0.002, false),
                FavouriteCurrency("BTC", "USD", "7627.411", 27.000, true)))
                .delay(1200L, TimeUnit.MILLISECONDS)
    }
}