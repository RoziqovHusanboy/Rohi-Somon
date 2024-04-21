package tj.tajsoft.loyalrsn.presintation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.tajsoft.loyalrsn.databinding.ItemAksiyaRvBinding

class HomeAksiyaAdapter(val list:ArrayList<Int>) :
    RecyclerView.Adapter<HomeAksiyaAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemAksiyaRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            Glide.with(binding.root).load(image).into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemAksiyaRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }


}

