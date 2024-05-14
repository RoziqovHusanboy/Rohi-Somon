package tj.tajsoft.loyalrsn.presintation.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import okhttp3.internal.notifyAll
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.databinding.ItemReportRvBinding

class TransactionReportAdapter(val list :List<ResponseTransaction>):RecyclerView.Adapter<TransactionReportAdapter.ViewHolder>() {
    inner class ViewHolder(val binding:ItemReportRvBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:ResponseTransaction) = with(binding){
            textViewVidToplivo.text = item.items.last().name
            textViewLitr.text = item.items.last().count
            textViewSumma.text = item.items.last().summa
            textViewBonus.text = item.cashback
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemReportRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
     }
}