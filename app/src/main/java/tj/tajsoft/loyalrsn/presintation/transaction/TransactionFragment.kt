package tj.tajsoft.loyalrsn.presintation.transaction

import android.opengl.Visibility
import android.os.Bundle
 import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.databinding.FragmentTransactionBinding

@AndroidEntryPoint
class TransactionFragment:Fragment() {

    private lateinit var binding: FragmentTransactionBinding
    private val viewModel by viewModels<TransactionViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageViewBack.setOnClickListener{
            findNavController().popBackStack()
        }

        UI()

    }

    private fun UI() {
        viewModel.transaction.observe(viewLifecycleOwner){

            binding.LayoutTransaction.isVisible = it!=null
            it?: return@observe

            binding.recyclerViewTransaction.adapter = TransactionAdapter(it)
            binding.recyclerviewReport.adapter = TransactionReportAdapter(it)
        }
        viewModel.loading.observe(viewLifecycleOwner){
            binding.loading.root.isVisible = it
         }
        viewModel.error.observe(viewLifecycleOwner){
            binding.error.root.isVisible = it

        }

    }

}