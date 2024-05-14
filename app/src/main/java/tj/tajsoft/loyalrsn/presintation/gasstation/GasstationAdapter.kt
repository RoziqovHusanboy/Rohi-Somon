package tj.tajsoft.loyalrsn.presintation.gasstation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tj.tajsoft.loyalrsn.data.remote.model.branches.Data
import tj.tajsoft.loyalrsn.data.remote.model.branches.ResponseBranches
import tj.tajsoft.loyalrsn.databinding.ItemGasstationRecyclerviewBinding

class GasstationAdapter(val list :ResponseBranches,val onClick:(data: Data)->Unit):RecyclerView.Adapter<GasstationAdapter.ViewHolderGasstation>() {
    inner class ViewHolderGasstation(val binding:ItemGasstationRecyclerviewBinding):ViewHolder(binding.root){
        fun bind(data: Data) = with(binding){
            binding.textViewName.text = data.title
            binding.textViewDesc.text = data.description
            binding.recyclerview.adapter= GasstationItemFuelAdapter(data.fuels)
            binding.imageRight.setOnClickListener {
                onClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderGasstation(
        ItemGasstationRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = list.data.size

    override fun onBindViewHolder(holder: ViewHolderGasstation, position: Int) {
       holder.bind(list.data[position])
    }
}