package tj.tajsoft.loyalrsn.presintation.splash_screen

import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.databinding.SplashScreenBinding

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {
    private lateinit var binding: SplashScreenBinding
    private val viewModel by viewModels<SplashScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SplashScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        android.os.Handler(Looper.getMainLooper()).postDelayed({
            viewModel.hasNumber.observe(viewLifecycleOwner){
                if (it == false){
                    findNavController().navigate(SplashScreenFragmentDirections.toCheckNumberFragment())
                }else{
                    findNavController().navigate(SplashScreenFragmentDirections.toLogInFragment())
                }
            }

        },2000)
    }
}