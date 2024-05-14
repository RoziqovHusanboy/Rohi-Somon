package tj.tajsoft.loyalrsn.presintation.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.FragmentRegisterTwoBinding

@AndroidEntryPoint
class RegisterTwoFragment : Fragment() {
    private lateinit var binding: FragmentRegisterTwoBinding
    private var birthText: String? = null
    private var nameText: String? = null
    private var secondText: String? = null
    private var checkedRadioButton: String? = null
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            birthText = it.getString("birth")
            nameText = it.getString("name")
            secondText = it.getString("second_name")
            Log.d(
                "FRAGMENT_REGISTER_TWO",
                "FRAGMENT_REGISTER_TWO: $birthText :, $nameText :, $secondText "
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterTwoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         UI()
       subscribeToLiveData()
     }

    private fun subscribeToLiveData() {
        viewModel.response.observe(viewLifecycleOwner){
            binding.error.root.isVisible =false
            it.userId?.let {
                findNavController().navigate(RegisterTwoFragmentDirections.actionRegisterTwoFragmentToHomeFragment())
            }
        }
        viewModel.loading.observe(viewLifecycleOwner){
            binding.loading.root.isVisible = it
        }

        viewModel.error.observe(viewLifecycleOwner){
            binding.error.root.isVisible =true
         }

    }

    private fun UI() {
        showButton()
        checkRadioButton()
        binding.checkboxAgree.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                updateButtonColor()
            }

        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun checkRadioButton() {
        checkedRadioButton = String()
        binding.radioGroup.setOnCheckedChangeListener { group, checkId ->
            when (checkId) {
                R.id.checkboxMen -> {
                    checkedRadioButton = "1"
                }

                R.id.checkboxWomen -> {
                    checkedRadioButton = "2"
                }
            }
            Log.d("checkedRadioButton", "checkRadioButton:$checkedRadioButton ")

        }
    }

    private fun updateButtonColor() {
        val name = nameText + secondText
         val birthText = birthText!!

        val isEditTextEmpty = binding.edittextCity.text.isNullOrEmpty() ||
                binding.edittextCar.text.isNullOrEmpty() ||
                binding.edittextPassword.text.isNullOrEmpty() || binding.radioGroup.checkedRadioButtonId == -1 || !binding.checkboxAgree.isChecked

        val buttonColorRes = if (isEditTextEmpty) R.color.button_back else R.color.card_background
        binding.buttonRegister.setBackgroundColor(requireContext().getColor(buttonColorRes))
        if (!isEditTextEmpty) {
            binding.buttonRegister.setOnClickListener {
                viewModel.register(
                    name,
                    binding.edittextCar.text.toString(),
                    "",
                    birthText,
                    checkedRadioButton!!,
                    binding.edittextPassword.text.toString(),
                    binding.edittextCity.text.toString()
                )

            }

        }

    }

    private fun showButton() {
        edittextWhatcher(binding.edittextCity)
        edittextWhatcher(binding.edittextCar)
        edittextWhatcher(binding.edittextPassword)

    }

    private fun edittextWhatcher(text: EditText) {

        text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (text.text.toString().trim().isNullOrEmpty()) {
                    text.setBackgroundResource(R.drawable.fragment_register_edittext_background_is_empty)
                } else {
                    text.setBackgroundResource(R.drawable.fragment_register_edittext_background)
                }
                updateButtonColor()
            }
        })
    }


}