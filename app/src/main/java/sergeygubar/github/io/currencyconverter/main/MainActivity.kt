package sergeygubar.github.io.currencyconverter.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import sergeygubar.github.io.currencyconverter.R
import sergeygubar.github.io.currencyconverter.convert.ConvertFragment
import sergeygubar.github.io.currencyconverter.extensions.executeTransaction
import sergeygubar.github.io.currencyconverter.extensions.fragmentExists
import sergeygubar.github.io.currencyconverter.favourites.FavouritesFragment
import sergeygubar.github.io.currencyconverter.settings.SettingsFragment

class MainActivity : AppCompatActivity(), MainActivityView {

    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // DI users, please don't cry
        presenter = MainActivityPresenter(this)
        showFavouritesFragment()

        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            presenter.onBottomNavItemSelected(item)
            true
        }
    }

    override fun showFavouritesFragment() {
        showFragment(FavouritesFragment.newInstance())
    }

    override fun showConvertFragment() {
        showFragment(ConvertFragment.newInstance())
    }

    override fun showSettingsFragment() {
        showFragment(SettingsFragment.newInstance())
    }

    private fun showFragment(fragment: Fragment) {
        if (!supportFragmentManager.fragmentExists(R.id.main_container)) {
            supportFragmentManager.executeTransaction {
                add(R.id.main_container, fragment)
            }
        } else {
            supportFragmentManager.executeTransaction {
                replace(R.id.main_container, fragment)
            }
        }
    }

}
