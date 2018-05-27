package sergeygubar.github.io.currencyconverter.extensions

import android.content.Context
import android.view.LayoutInflater


val Context.inflater
    get() = LayoutInflater.from(this)