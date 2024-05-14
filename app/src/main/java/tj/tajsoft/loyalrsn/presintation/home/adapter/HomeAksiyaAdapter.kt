package tj.tajsoft.loyalrsn.presintation.home.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.squareup.picasso.Picasso
import tj.tajsoft.loyalrsn.common.Constant
import tj.tajsoft.loyalrsn.data.remote.model.sale.Data
import tj.tajsoft.loyalrsn.data.remote.model.sale.ResponseSale
import tj.tajsoft.loyalrsn.databinding.ItemAksiyaRvBinding

class HomeAksiyaAdapter(val list:ResponseSale, val loading: Boolean,val onClick:(id:Int)->Unit  ) :

    RecyclerView.Adapter<HomeAksiyaAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemAksiyaRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data:Data) {
            val constImage = Constant.IMAGE_URL
            val image = "$constImage${data.img}"

            Glide.with(binding.root).load(image).listener(object :RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressbarImage.isVisible = false
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressbarImage.isVisible = false
                    return false
                }

            }).into(binding.image)

            binding.root.setOnClickListener{
                onClick(data.id)
            }
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemAksiyaRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = list.data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list.data[position])
    }


}

