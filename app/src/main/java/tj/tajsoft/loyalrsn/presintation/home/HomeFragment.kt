package tj.tajsoft.loyalrsn.presintation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.presintation.home.adapter.HomeAksiyaAdapter
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUser()
        Log.d("TAG", "onCreate: viewModel.getUser()")
    }

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
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() = with(binding) {
        viewModel.product.observe(viewLifecycleOwner){user->
            countTv.text = user.data.card.balans.toString()
            name.text = user.data.name
            idName.text = getString(R.string.home_fragment_id_name,user.data.id.toString())
            barakatTV.text = user.data.card.cardType

        }
    }

    private fun UI() {
        binding.showAll.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toTransactionFragment())
        }
     //   binding.recyclerview.adapter = HomeAksiyaAdapter(list = list())
        binding.QRcode.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQRCodeFragment())
        }
    }



}