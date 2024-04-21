package tj.tajsoft.loyalrsn.presintation.discount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
 import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.FragmentDiscountBinding

@AndroidEntryPoint
class DiscountFragment : Fragment() {

    private lateinit var binding:FragmentDiscountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscountBinding.inflate(inflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UI()
    }

    private fun UI() = with(binding) {
        recyclerview.adapter = DiscountAdapter(list(),::onClick)
     }

    fun list(): ArrayList<Int> {
        val list = arrayListOf<Int>(
            R.drawable.a1,
            R.drawable.a2,
            R.drawable.a4,
            R.drawable.p2,
            R.drawable.person1
        )
        return list
    }

    fun onClick(){
        findNavController().navigate(DiscountFragmentDirections.actionDiscountFragmentToDetailDiscountFragment())
    }

}