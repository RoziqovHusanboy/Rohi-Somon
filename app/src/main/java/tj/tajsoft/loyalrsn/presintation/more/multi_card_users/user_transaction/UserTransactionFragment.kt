package tj.tajsoft.loyalrsn.presintation.more.multi_card_users.user_transaction

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.databinding.FragmentUserTransactionBinding
import tj.tajsoft.loyalrsn.presintation.more.multi_card_users.user_transaction.adapter.AllTransactionItemTransactionAdapter
import tj.tajsoft.loyalrsn.presintation.more.multi_card_users.user_transaction.adapter.UserTransactionFuelAdapter

@AndroidEntryPoint
class UserTransactionFragment:Fragment() {
    private lateinit var binding:FragmentUserTransactionBinding
    private val viewModel:UserTransactionViewModel by viewModels()
    private var id:Int = 0
    private var name:String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
             id =  it.getInt("id")
            name = it.getString("name")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserTransactionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        viewModel.getParentUsersTransaction(id)
        UI()
    }

    private fun subscribeToLiveData() {
        viewModel.parentUserTransaction.observe(viewLifecycleOwner){
            binding.name.text = name
            binding.recyclerviewAllTransaction.adapter = UserTransactionFuelAdapter(it)
            var totalSumma = 0
            var totalLeter = 0
            it.forEach {
                it.items.forEach {
                    totalSumma += it.summa.toDouble().toInt()
                    totalLeter += it.count.toDouble().toInt()

                }
            }

            binding.totalSumma.text = "$totalSumma c"
            binding.totalLiter.text = "$totalLeter л"

            binding.recyclerviewAllTransactionItemTransaction.adapter = AllTransactionItemTransactionAdapter(it)

        }

        viewModel.loading.observe(viewLifecycleOwner){
            binding.loading.root.isVisible = it
            binding.cointainer.isVisible = it == false
        }

    }

    private fun UI() = with(binding) {
        back.setOnClickListener {
            findNavController().popBackStack()
        }
        imageIconRight.setOnClickListener {
            recyclerviewAllTransaction.isVisible = true
            it.isVisible = false
            binding.totalSumma.isVisible = true
            binding.totalLiter.isVisible = true
            binding.totalList.isVisible = true
            imageIconDown.isVisible = true
        }
        imageIconDown.setOnClickListener {
            it.isVisible = false
            imageIconRight.isVisible = true
            binding.totalSumma.isVisible = false
            binding.totalLiter.isVisible = false
            binding.totalList.isVisible = false
            recyclerviewAllTransaction.isVisible = false
        }


    }
}