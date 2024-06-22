package tj.tajsoft.loyalrsn.presintation.more

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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
        viewModel.user.observe(viewLifecycleOwner){
            if (it.isNullOrEmpty().not()){
                if (it.first().status == "disable"){
                    binding.statusConstraint.isVisible = false
                }else{
                    binding.statusConstraint.isVisible = true
                    binding.vidStatus.text = it.firstOrNull()?.cardType
                }
                for (userEntity in it) {
                    binding.myCards.isVisible =  userEntity.classX=="org"

                }
            }


        }
        UI()
    }

    private fun UI(){

        binding.apply {

                binding.myCards.setOnClickListener {
                     findNavController().navigate(MoreFragmentDirections.toMultiCardFragment())
            }

            profile.setOnClickListener {
                findNavController().navigate(MoreFragmentDirections.actionMoreFragmentToProfileFragment())
            }
            logout.setOnClickListener {
                viewModel.clearNumber()
                viewModel.clearDataBase()
                requireActivity().finish()
             }
            question.setOnClickListener {
                Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/rsnpodershka")).apply {
                    startActivity(this)
                }
            }
            site.setOnClickListener {
                Intent(Intent.ACTION_VIEW, Uri.parse("https://rsn.tj/")).apply {
                    startActivity(this)
                }
            }
        }

    }
}