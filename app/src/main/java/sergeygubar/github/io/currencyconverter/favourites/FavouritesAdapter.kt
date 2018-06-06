package sergeygubar.github.io.currencyconverter.favourites

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.favourite_item.view.*
import org.jetbrains.anko.textColor
import sergeygubar.github.io.currencyconverter.R
import sergeygubar.github.io.currencyconverter.entities.FavouriteCurrency
import sergeygubar.github.io.currencyconverter.extensions.inflater

class FavouritesAdapter(private val data: MutableList<FavouriteCurrency>): RecyclerView.Adapter<FavouritesAdapter.FavouriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        return FavouriteViewHolder(parent.context.inflater.inflate(R.layout.favourite_item, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(position)
    }

    fun addData(currency: FavouriteCurrency) {
        data.add(currency)
        notifyDataSetChanged()
    }


    inner class FavouriteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            val currency = data[position]
            itemView.from_text_view.text = currency.from
            itemView.to_text_view.text = currency.to
            itemView.change_text_view.text = currency.change.toString()
            itemView.rate_text_view.text = currency.rate
            itemView.change_text_view.textColor = if (currency.isUp) {
                Color.GREEN
            } else {
                Color.RED
            }
        }
    }
}