package tj.tajsoft.loyalrsn.presintation.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.databinding.FragmentTransactionItemTransactionBinding

class TransactionAdapter(val list: ArrayList<ResponseTransaction>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: FragmentTransactionItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseTransaction) = with(binding) {
            dataTransaction.text = item.createAdd.date
            vidToplivoTransaction.text = root.context.getString(
                R.string.fragment_transaction_item_vid_toplivo_text,
                item.items.last().name
            )
            cityTransaction.text = item.address
//            val summa = if ( item.items.last().summa!=null) item.items.last().summa.substring(0,3) else item.items.last().summa
            summaTransaction.text = root.context.getString(
                R.string.fragment_transaction_item_summa_count,item.items.last().summa)
            keshbek.text = root.context.getString(
                R.string.fragment_transaction_item_keshbek_text,
                item.cashback
            )
            countLitrTransaction.text = root.context.getString(
                R.string.fragment_transaction_item_count_litr,
                item.items.last().count
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        FragmentTransactionItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }


}