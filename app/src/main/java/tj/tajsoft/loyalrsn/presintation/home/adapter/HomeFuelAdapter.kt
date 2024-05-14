package tj.tajsoft.loyalrsn.presintation.home.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.tajsoft.loyalrsn.data.remote.model.product.fuel.DataFuel
import tj.tajsoft.loyalrsn.data.remote.model.product.fuel.ResponseFuel
import tj.tajsoft.loyalrsn.databinding.ItemFuelRecyclerViewBinding

class HomeFuelAdapter(val list: ResponseFuel):RecyclerView.Adapter<HomeFuelAdapter.ViewHolder>() {
    inner class ViewHolder(val binding:ItemFuelRecyclerViewBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(dataFuel: DataFuel) = with(binding){
            val color = Color.parseColor(dataFuel.backgroundcolor)
            cardLayout.setCardBackgroundColor(color)
            cardText.setCardBackgroundColor(color)
            textName.text = dataFuel.name
            textviewPrice.text = dataFuel.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemFuelRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = list.data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list.data[position])
     }
}