package sergeygubar.github.io.currencyconverter.main

import android.view.MenuItem
import sergeygubar.github.io.currencyconverter.R

class MainActivityPresenter(private val view: MainActivityView) {

    fun onBottomNavItemSelected(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.action_favorites -> {
                view.showFavouritesFragment()
            }
            R.id.action_convert -> {
                view.showConvertFragment()
            }
            R.id.action_settings -> {
                view.showSettingsFragment()
            }
        }
    }
}