package tj.tajsoft.loyalrsn.presintation.register

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.FragmentRegisterOneBinding

@AndroidEntryPoint
class RegisterOneFragment : Fragment() {
    private lateinit var binding: FragmentRegisterOneBinding
    private val TAG = "TAG"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterOneBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UI()
     }


    private fun UI() {
        showButton()
        openCalendar()
    }

    override fun onResume() {
        super.onResume()
        updateButtonColor()
    }

    private fun openCalendar() {
        binding.imageViewCalendar.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(), object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(
                        view: DatePicker?,
                        year: Int,
                        month: Int,
                        dayOfMonth: Int
                    ) {
                        val dat = (dayOfMonth.toString() + "." + (month + 1) + "." + (year))
                        binding.edittextBirth.setText(dat)
                         updateButtonColor()
                    }

                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }

    private fun showButton() {
        edittextWhatcher(binding.edittextName)
        edittextWhatcher(binding.edittextSecondName)


    }

    @SuppressLint("SuspiciousIndentation")
    private fun updateButtonColor() {
        val isEditTextEmpty = binding.edittextName.text.isNullOrEmpty() ||
                binding.edittextSecondName.text.isNullOrEmpty() ||
                binding.edittextBirth.text.isNullOrEmpty()

        val buttonColorRes = if (isEditTextEmpty) R.color.button_back else R.color.card_background
        binding.buttonNext.setBackgroundColor(requireContext().getColor(buttonColorRes))

        if (!isEditTextEmpty) {
           val sendNameText =  binding.edittextName.text.toString()
             Log.d("FRAGMENT_ONE", "FRAGMENT_ONE: $sendNameText")
            val sendSecondText = binding.edittextSecondName.text.toString()
             Log.d("FRAGMENT_ONE", "FRAGMENT_ONE: $sendSecondText")
            binding.buttonNext.setOnClickListener {
                findNavController().navigate(RegisterOneFragmentDirections.actionRegisterOneFragmentToRegisterTwoFragment(sendNameText,sendSecondText,binding.edittextBirth.text.toString()))
            }
        }
    }

    private fun edittextWhatcher(text: EditText) {

        text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (text.text.toString().trim().isNullOrEmpty()) {
                    text.setBackgroundResource(R.drawable.fragment_register_edittext_background_is_empty)
                    updateButtonColor()
                } else {
                    text.setBackgroundResource(R.drawable.fragment_register_edittext_background)
                    updateButtonColor()
                }

            }
        })
    }

}