package tj.tajsoft.loyalrsn.presintation.splash_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.databinding.SplashScreenBinding

@SuppressLint("CustomSplashScreen")
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
        //val viewModel = ViewModelProvider(this)[SplashScreenViewModel::class.java]

        android.os.Handler(Looper.getMainLooper()).postDelayed({
            viewModel.hasNumber.observe(viewLifecycleOwner){
                Log.d("TAG", "onViewCreated: $it")
                if (!it.isNullOrEmpty()){
                    findNavController().navigate(SplashScreenFragmentDirections.toLogInFragment())
                }else{
                    findNavController().navigate(SplashScreenFragmentDirections.toCheckNumberFragment())
                }
            }

        },2000)
    }
}