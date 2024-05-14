package tj.tajsoft.loyalrsn.presintation.check_number

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.databinding.FragmentCheckNumberBinding

@AndroidEntryPoint
class CheckNumberFragment : Fragment() {
    private lateinit var binding: FragmentCheckNumberBinding
    private val viewModel by viewModels<CheckNumberViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckNumberBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lengthNumber = binding.editTextNumber
        lengthNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length != 9) {
                    if (!s.toString().isDigitsOnly()){
                        binding.imageViewIconNextVisible.isVisible = false
                        binding.imageViewIconNextUnVisible.isVisible = true
                        Toast.makeText(requireContext(), "Number should be digit", Toast.LENGTH_SHORT).show()
                    }
                } else {

                     binding.imageViewIconNextVisible.isVisible = true
                    binding.imageViewIconNextUnVisible.isVisible = false
                     binding.imageViewIconNextVisible.setOnClickListener {
                         Log.d("TAG", "afterTextChanged:$s ")
                         viewModel.checkPhoneNumber(s.toString())
                         findNavController().navigate(CheckNumberFragmentDirections.toOtpFragment(s.toString()))
                    }
                }
            }

        })

    }

}