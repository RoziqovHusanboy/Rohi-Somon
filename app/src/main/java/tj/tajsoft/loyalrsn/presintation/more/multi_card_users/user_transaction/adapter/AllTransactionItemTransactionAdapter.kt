package tj.tajsoft.loyalrsn.presintation.more.multi_card_users.user_transaction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.databinding.AllTransactionItemTransactionBinding

class AllTransactionItemTransactionAdapter(
    val list:Array<ResponseTransaction>
):RecyclerView.Adapter<AllTransactionItemTransactionAdapter.ViewHolderTransaction>() {
    inner class ViewHolderTransaction(val binding:AllTransactionItemTransactionBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:ResponseTransaction){
            binding.dataTransaction.text = item.createAdd!!.date
            binding.cityTransaction.text =item.address
            binding.countLitrTransaction.text = "${item.items.last().count} л"
            binding.vidToplivoTransaction.text = "${item.items.last().name} /"
            binding.keshbek.text = "${item.cashback} TJS"
            binding.summaTransaction.text = "${item.items.last().summa} TJS/"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =ViewHolderTransaction(
        AllTransactionItemTransactionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolderTransaction, position: Int) {
       holder.bind(list[position])
    }

}