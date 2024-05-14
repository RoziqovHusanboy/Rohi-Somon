package tj.tajsoft.loyalrsn.presintation.gasstation

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tj.tajsoft.loyalrsn.data.remote.model.product.fuel.DataFuel
import tj.tajsoft.loyalrsn.data.remote.model.product.fuel.ResponseFuel
import tj.tajsoft.loyalrsn.databinding.ItemGasstationDetailItemFuelRecyclerViewBinding

class DetailGasstationAdapter(val list:ResponseFuel):RecyclerView.Adapter<DetailGasstationAdapter.ViewHolderDetail>() {
    inner class ViewHolderDetail(val binding: ItemGasstationDetailItemFuelRecyclerViewBinding):ViewHolder(binding.root){
        fun bind(dataFuel: DataFuel) = with(binding){
            val color = Color.parseColor(dataFuel.backgroundcolor)
            binding.cardLayout.setCardBackgroundColor(color)
            binding.cardText.setCardBackgroundColor(color)
            binding.textName.text = dataFuel.name
            binding.textviewPrice.text = dataFuel.price.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderDetail(
        ItemGasstationDetailItemFuelRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )


    override fun getItemCount() =list.data.size

    override fun onBindViewHolder(holder: ViewHolderDetail, position: Int) {
        holder.bind(list.data[position])
    }
}