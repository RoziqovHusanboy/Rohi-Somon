package tj.tajsoft.loyalrsn.presintation.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import tj.tajsoft.loyalrsn.data.local.room.entity.userEntity.UserEntity
import tj.tajsoft.loyalrsn.databinding.HomeCardAdapterBinding

class HomeCardAdapter(
    val list: List<UserEntity>,
    val onclickQRCode:(barcode:String)->Unit
) : RecyclerView.Adapter<HomeCardAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: HomeCardAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserEntity) = with(binding) {
            name.text = item.name
            countTv.text = item.balanceCard.toString()
           idName.text = "N ${item.id}"

            layoutCard.setOnClickListener {
                item.barcodeCard.let {
                    Log.d("QRCODE", "bind: $it")
                    it?.let {
                        onclickQRCode(it)

                    }
                }
            }
            if (item.status == "active") {
                binding.layoutActiveCard.isVisible = true
                binding.layoutDismissCard.isVisible = false
             }

            when (item.classX) {
                "org" -> {
                    binding.cardType.text =item.name
                }
                "employee" -> {
                    binding.cardType.text = item.owner
                }
                "person" -> {
                    binding.cardType.text = item.cardType
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        HomeCardAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}