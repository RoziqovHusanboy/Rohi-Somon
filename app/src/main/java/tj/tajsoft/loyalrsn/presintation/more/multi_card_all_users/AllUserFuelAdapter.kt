package tj.tajsoft.loyalrsn.presintation.more.multi_card_all_users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.databinding.AllUsersFuelRcBinding

class AllUserFuelAdapter(
    val list:Array<ResponseTransaction>
):RecyclerView.Adapter<AllUserFuelAdapter.ViewHolder>() {

    inner class ViewHolder(val binding:AllUsersFuelRcBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:ResponseTransaction){

            binding.textLiter.text = "${item.items.last().count} л"
            binding.textSumma.text = "${item.items.last().summa} c"
            binding.textVid.text = "${item.items.last().name} "
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AllUsersFuelRcBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() =list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.bind(list[position])
    }

}