package sergeygubar.github.io.currencyconverter.extensions

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import sergeygubar.github.io.currencyconverter.R

fun FragmentManager.executeTransaction(block: FragmentTransaction.() -> Unit) {
    val transaction = beginTransaction()
    transaction.block()
    transaction.commitAllowingStateLoss()
}

fun FragmentManager.fragmentExists(id: Int): Boolean {
    return findFragmentById(R.id.main_container) != null
}