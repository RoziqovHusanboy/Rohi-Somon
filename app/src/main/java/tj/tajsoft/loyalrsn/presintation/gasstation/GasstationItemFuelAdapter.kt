package tj.tajsoft.loyalrsn.presintation.gasstation

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.tajsoft.loyalrsn.data.remote.model.branches.Fuel
import tj.tajsoft.loyalrsn.databinding.ItemGasstationItemFuelRecyclerviewBinding

class GasstationItemFuelAdapter(val list: List<Fuel>) :
    RecyclerView.Adapter<GasstationItemFuelAdapter.ViewHolderGasstationItemFuel>() {
    inner class ViewHolderGasstationItemFuel(val binding: ItemGasstationItemFuelRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fuel: Fuel) = with(binding) {
            val color = Color.parseColor(fuel.color)
            binding.cardText.setCardBackgroundColor(color)
            binding.textName.text = fuel.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolderGasstationItemFuel(
            ItemGasstationItemFuelRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolderGasstationItemFuel, position: Int) {
        holder.bind(list[position])
    }
}