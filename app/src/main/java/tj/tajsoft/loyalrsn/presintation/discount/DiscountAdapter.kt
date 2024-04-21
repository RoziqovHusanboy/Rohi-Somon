package tj.tajsoft.loyalrsn.presintation.discount

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
 import tj.tajsoft.loyalrsn.databinding.ItemDiscountRvBinding

class DiscountAdapter(
    val list: ArrayList<Int>,
    val onclick:()->Unit
) : RecyclerView.Adapter<DiscountAdapter.VH>() {
    inner class VH(val binding: ItemDiscountRvBinding) : ViewHolder(binding.root) {
        fun bind(item:Int){
            Glide.with(binding.root).load(item).into(binding.image)
            binding.root.setOnClickListener {
                onclick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemDiscountRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
    }
}