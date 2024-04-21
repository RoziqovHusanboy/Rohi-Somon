package tj.tajsoft.loyalrsn.presintation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.presintation.home.adapter.HomeAksiyaAdapter
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UI()
    }

    private fun UI() {
        binding.showAll.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toTransactionFragment())
        }
        binding.recyclerview.adapter = HomeAksiyaAdapter(list = list())
        binding.QRcode.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQRCodeFragment())
        }
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

}