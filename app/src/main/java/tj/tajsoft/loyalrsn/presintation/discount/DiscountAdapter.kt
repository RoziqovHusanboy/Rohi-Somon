package tj.tajsoft.loyalrsn.presintation.discount

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import tj.tajsoft.loyalrsn.common.Constant
import tj.tajsoft.loyalrsn.data.remote.model.sale.Data
import tj.tajsoft.loyalrsn.data.remote.model.sale.ResponseSale
import tj.tajsoft.loyalrsn.databinding.ItemDiscountRvBinding

class DiscountAdapter(
    val list: ResponseSale,
    val onClick:(id:Int)->Unit
 ) : RecyclerView.Adapter<DiscountAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemDiscountRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data){
            val constImage = Constant.IMAGE_URL
            val image = "$constImage${data.img}"
            Log.d("repo", "bind: $image")
            Glide.with(binding.root).load(image).listener(object :RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.isVisible = false
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.isVisible = false
                    return false
                }

            }).into(binding.image)
             binding.root.setOnClickListener {
               onClick(data.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemDiscountRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = list.data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list.data[position])
    }
}