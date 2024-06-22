package tj.tajsoft.loyalrsn.presintation.more.multi_card_users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.databinding.FragmentMultiCardBinding
import tj.tajsoft.loyalrsn.presintation.more.multi_card_users.adapter.WithMoneyAdapter

@AndroidEntryPoint
class MultiCardFragment:Fragment() {
    private lateinit var binding:FragmentMultiCardBinding
    private val viewModel:MultiCardViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMultiCardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        UI()
    }

    private fun UI() = with(binding) {
        back.setOnClickListener {
            findNavController().popBackStack()
        }
        buttonReport.setOnClickListener {
            findNavController().navigate(MultiCardFragmentDirections.actionMultiCardFragmentToAllUserFragment())
        }
    }

    private fun subscribeToLiveData() {
        viewModel.parentUser.observe(viewLifecycleOwner){
            if (it == null) return@observe
            it.forEach {
                if (it.fond.toDouble() == 1.0){
                    binding.layoutMoney.isVisible = true
                    binding.layoutLiter.isVisible = false
                    binding.count.text = it.saldo.toString()
                    binding.recyclerviewWithMoney.adapter = WithMoneyAdapter(it.employees,::onclick)
                }

                if (it.fond == 0){
                    binding.layoutLiter.isVisible = true
                    binding.layoutMoney.isVisible = false
                }
            }
        }


        viewModel.loading.observe(viewLifecycleOwner){
            binding.loading.root.isVisible = it
            if (it == false){
                binding.container.isVisible = true
            }
            if (it == true){
                binding.container.isVisible =false
            }
        }
    }

    private fun onclick(id:Int,name:String){
        findNavController().navigate(MultiCardFragmentDirections.toUserTransactionFragment(id,name))
    }
}