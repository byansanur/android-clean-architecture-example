package id.byandev.hanifahapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.byandev.hanifahapp.R
import id.byandev.hanifahapp.databinding.ItemCoinsBinding
import id.byandev.hanifahapp.domain.model.Coin
import javax.inject.Inject

/**
 * Created by byansanur on 9/7/2021.
 * ratbyansanur81@gmail.com
 */
class AdapterCoins : RecyclerView.Adapter<AdapterCoins.Holder>() {

    private val list = ArrayList<Coin>()

    fun setData(list: List<Coin>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCoinsBinding.bind(itemView)
        fun bind(coin: Coin) {
            with(binding) {
                tvName.text = coin.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_coins, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val listed = list[position]
        holder.bind(listed)
    }

    override fun getItemCount(): Int = list.size
}