package tj.tajsoft.loyalrsn.presintation.more.multi_card_all_users

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tj.tajsoft.loyalrsn.data.remote.model.parent_user.Employee
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.databinding.AllUsersUserFuelsBinding
import tj.tajsoft.loyalrsn.domain.model.ModelResponseParent

class AllUserAdapter(
    val onclick: ItemUsersOnclick,
    val context: Context
) : RecyclerView.Adapter<AllUserAdapter.ViewHolder>() {
    var parentTransactionList: List<ResponseTransaction>? = emptyList()
    var userParentList: List<Employee> = emptyList()

    inner class ViewHolder(val binding: AllUsersUserFuelsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userParent:Employee,parentTransaction:List< ResponseTransaction>?)
        {
            binding.name.text = userParent.name
            binding.root.setOnClickListener {
//                onclick.onClick(userParent.id)
            }

            if (parentTransaction !=null)
            {
                parentTransaction.forEach {
                    if (it.userId == userParent.id.toString())
                    {
                        binding.recyclerviewAllUserFuel.adapter =
                            AllUserFuelAdapter(parentTransaction.toTypedArray())
                        binding.recyclerviewAllUserFuel.layoutManager = LinearLayoutManager(context)
                        binding.recyclerviewAllUserFuel.setHasFixedSize(true)

                        var totalSumma = 0
                        var totalLeter = 0

                        for (transaction in  parentTransaction) {
                            Log.d("transaction", "bindtransaction: ${transaction.userId}")
                            for (transactionItem in transaction.items) {
                                Log.d("transaction", "bindtransactionItem: ${transactionItem.name}")
                                totalSumma += transactionItem.summa.toDouble().toInt()
                                totalLeter += transactionItem.count.toDouble().toInt()
                            }
                        }
                        binding.textTotalLitr.text = "$totalLeter л"
                        binding.textTotalSumma.text = "$totalSumma c"

                    }
                }

            }

            binding.imageIconRight.setOnClickListener {
//                onclick.onClick(userParent.id)
                it.isVisible = false
                binding.imageIconDown.isVisible = true
                binding.recyclerviewAllUserFuel.isVisible = true
                binding.layoutTotal.isVisible = true
            }

            binding.imageIconDown.setOnClickListener {
                it.isVisible = false
                binding.imageIconRight.isVisible = true
                binding.recyclerviewAllUserFuel.isVisible = false
                binding.layoutTotal.isVisible = false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(
            AllUsersUserFuelsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = userParentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(userParentList[position],parentTransactionList)
    }

    fun updateData(parentList: ModelResponseParent)
    {
        parentList.parentUser?.forEach {
            userParentList = it.employees
        }
        parentTransactionList = parentList.parentUserTransaction
        notifyDataSetChanged()
    }
}
