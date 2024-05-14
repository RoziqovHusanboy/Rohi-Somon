package tj.tajsoft.loyalrsn.presintation.discount

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.squareup.picasso.Picasso
import tj.tajsoft.loyalrsn.common.Constant
import tj.tajsoft.loyalrsn.data.remote.model.sale.Data
import tj.tajsoft.loyalrsn.data.remote.model.sale.ResponseSale
import tj.tajsoft.loyalrsn.databinding.ItemDiscountRvBinding

class DiscountAdapter(
    val list: ResponseSale,
    val onclick:(data:Data)->Unit,
 ) : RecyclerView.Adapter<DiscountAdapter.VH>() {
    inner class VH(val binding: ItemDiscountRvBinding) : ViewHolder(binding.root) {
        fun bind(data: Data){
            val constImage = Constant.IMAGE_URL
            val image = "$constImage${data.img}"
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
                onclick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemDiscountRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = list.data.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list.data[position])
    }
}