package tj.tajsoft.loyalrsn.presintation.more.multi_card_users.user_transaction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.databinding.UserAllTransactionFuelRcBinding

class UserTransactionFuelAdapter(
  val list:Array<ResponseTransaction>
) :RecyclerView.Adapter<UserTransactionFuelAdapter.ViewHolder>() {
    inner class ViewHolder(val binding:UserAllTransactionFuelRcBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:ResponseTransaction){
            binding.textViewVid.text = item.items.last().name
            binding.textViewLitr.text = "${item.items.last().count} л"
            binding.textViewSumma.text = "${item.items.last().summa} c"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        UserAllTransactionFuelRcBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )
    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}