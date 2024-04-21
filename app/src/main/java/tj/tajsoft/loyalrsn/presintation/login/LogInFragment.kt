package tj.tajsoft.loyalrsn.presintation.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LogInFragment : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    private val viewModel by viewModels<LogInViewModel>()
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        password = String()
        viewModel.password.observe(viewLifecycleOwner){
            password = it
        }

        binding.pinView.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 4 && password == s.toString()){
                    findNavController().navigate(LogInFragmentDirections.toHomeFragment())
                }else{
                    if (s?.length == 4 && password != s.toString()){
                    context?.getColor(R.color.red)?.let { it1 -> binding.pinView.setLineColor(it1) }
                    binding.pinView.setTextColor(requireContext().getColor(R.color.red))
                    Toast.makeText(requireContext(), "Не провильно ввели код", Toast.LENGTH_SHORT).show()
                        LogInFragment()
                    }
                }
            }

        })
    }

}