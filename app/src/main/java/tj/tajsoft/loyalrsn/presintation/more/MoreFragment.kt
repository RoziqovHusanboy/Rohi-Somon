package tj.tajsoft.loyalrsn.presintation.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.databinding.FragmentMoreBinding

@AndroidEntryPoint
class MoreFragment:Fragment() {
    private lateinit var binding: FragmentMoreBinding
    private val viewModel by viewModels<MoreViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoreBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UI()
    }

    private fun UI(){

        binding.apply {

            profile.setOnClickListener {
                findNavController().navigate(MoreFragmentDirections.actionMoreFragmentToProfileFragment())
            }
            logout.setOnClickListener {
                viewModel.clearNumber()
                requireActivity().finish()
             }
        }

    }
}