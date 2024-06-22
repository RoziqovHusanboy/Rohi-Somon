package tj.tajsoft.loyalrsn.presintation.more.multi_card_users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.tajsoft.loyalrsn.data.remote.model.parent_user.Employee
import tj.tajsoft.loyalrsn.databinding.MultiCardItemMoneyRcBinding

class WithMoneyAdapter(
    val list:List<Employee>,
    val onCLick:(id:Int,name:String)->Unit
):RecyclerView.Adapter<WithMoneyAdapter.ViewHolderWithMoney>() {

    inner class ViewHolderWithMoney(val binding:MultiCardItemMoneyRcBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:Employee){
            binding.apply {
                countTv.text = item.cards.balans.toString()
                name.text = item.name
                idName.text =item.rId.toString()
                binding.root.setOnClickListener {
                    onCLick(item.id,item.name)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderWithMoney {
        return ViewHolderWithMoney(MultiCardItemMoneyRcBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolderWithMoney, position: Int) {
        holder.bind(list[position])
    }

}