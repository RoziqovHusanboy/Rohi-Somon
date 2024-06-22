package tj.tajsoft.loyalrsn.presintation.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.tajsoft.loyalrsn.databinding.ItemNotificationBodyBinding
import tj.tajsoft.loyalrsn.databinding.ItemNotificationHeaderBinding

class NotificationAdapter(
    val list:List<Any>
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when(list[position]){
            is NotificationModel.Header ->VIEW_TYPE_HEADER
            is NotificationModel.Body ->VIEW_TYPE_ITEM
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

      class HeaderViewHolder(val binding:ItemNotificationHeaderBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:NotificationModel.Header){
           binding.title.text = item.title
        }
    }
      class BodyViewHolder(val binding:ItemNotificationBodyBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:NotificationModel.Body){
            Glide.with(binding.root).load(item.icon).into(binding.imageIcon)
            binding.textViewTitle.text = item.title
            binding.textViewDesc.text = item.description
            binding.textViewData.text = item.data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            VIEW_TYPE_HEADER ->{
                HeaderViewHolder(ItemNotificationHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            VIEW_TYPE_ITEM->{
                BodyViewHolder(ItemNotificationBodyBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HeaderViewHolder->holder.bind(list[position] as NotificationModel.Header)
            is BodyViewHolder ->holder.bind(list[position] as NotificationModel.Body)
        }
     }
}