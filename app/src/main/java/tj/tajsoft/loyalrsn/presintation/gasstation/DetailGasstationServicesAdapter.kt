package tj.tajsoft.loyalrsn.presintation.gasstation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.tajsoft.loyalrsn.data.remote.model.branches.Service
import tj.tajsoft.loyalrsn.databinding.ItemGasstationDetailBinding

class DetailGasstationServicesAdapter(val list:List<Service>):RecyclerView.Adapter<DetailGasstationServicesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding:ItemGasstationDetailBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(service: Service){
            val putImage = "http://45.12.74.50/images/services/${service.img}"
            Glide.with(binding.root).load(putImage).into(binding.imageView)
            binding.textView.text = service.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemGasstationDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

}