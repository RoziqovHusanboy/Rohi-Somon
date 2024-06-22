package tj.tajsoft.loyalrsn.presintation.discount

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.data.local.room.entity.sale.SaleEntity
import tj.tajsoft.loyalrsn.data.remote.model.sale.Data
import tj.tajsoft.loyalrsn.databinding.FragmentDiscountBinding

@AndroidEntryPoint
class DiscountFragment : Fragment() {

    private lateinit var binding: FragmentDiscountBinding
    private val viewModel by viewModels<DiscountViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscountBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UI()
    }

    private fun UI() = with(binding) {
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            binding.layoutDiscount.isVisible = !loading
            binding.loading.root.isVisible = loading
        }
        viewModel.sale.observe(viewLifecycleOwner) { sale ->
            recyclerview.layoutManager = LinearLayoutManager(requireContext())
            val adapter = DiscountAdapter(sale,::onClick)
            recyclerview.adapter = adapter
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it == true) {
                Toast.makeText(requireContext(), "Your internet is not working", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }


    private fun onClick(data: Int) {
        findNavController().navigate(
            DiscountFragmentDirections.actionDiscountFragmentToDetailDiscountFragment(
                data
            )
        )
    }
}