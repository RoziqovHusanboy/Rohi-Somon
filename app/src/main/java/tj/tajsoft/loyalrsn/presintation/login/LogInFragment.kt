package tj.tajsoft.loyalrsn.presintation.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chaos.view.PinView
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.FragmentLoginBinding
import tj.tajsoft.loyalrsn.presintation.otp.OtpFragmentDirections

@AndroidEntryPoint
class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LogInViewModel>()


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
        binding.textViewforgotPassword.setOnClickListener {
            openDialogOtp()
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.loading.root.isVisible = it
            binding.layoutLogIn.isVisible = !it
        }

        viewModel.responseLogIn.observe(viewLifecycleOwner) {
            if (it.token != null) {
                findNavController().navigate(LogInFragmentDirections.toHomeFragment())
            } else {
                Toast.makeText(requireContext(), "Your password was un correct", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it == true) {
                Toast.makeText(requireContext(), "Не правильно ввели код", Toast.LENGTH_SHORT)
                    .show()
                binding.pinView.text!!.clear()
            }
        }


        binding.pinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 4) {
                    viewModel.checkLogIn(s.toString())
                }
            }

        })
    }

    private fun openDialogOtp() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.bottom_sheet_update_password_otp)
        val textdesc = dialog.findViewById<TextView>(R.id.textdesc)
        val pinViewBottomSheetPassword =
            dialog.findViewById<PinView>(R.id.pinViewBottomSheetPassword)
        val textViewTextCodeClicked = dialog.findViewById<TextView>(R.id.textViewTextCodeClicked)

        viewModel.phoneNumber.observe(viewLifecycleOwner) {
            textdesc?.text = requireContext().getString(
                R.string.item_bottom_sheet_title_desc_password_update,
                it
            )
        }
        viewModel.checkPhoneNumber()

        textViewTextCodeClicked?.setOnClickListener { view ->
            viewModel.checkPhoneNumber()
        }



        pinViewBottomSheetPassword?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val numberOtp = viewModel.numberOtp
                if (s?.length == 4 && numberOtp == s.toString()) {
                    dialog.dismiss()
                    openDialogUpdatePassword()


                }

                if (s?.length == 4 && numberOtp != s.toString()) {
                    context?.getColor(R.color.red)?.let { it1 -> binding.pinView.setLineColor(it1) }
                    binding.pinView.setTextColor(requireContext().getColor(R.color.red))
                    Toast.makeText(requireContext(), "Не провильно ввели код", Toast.LENGTH_SHORT)
                        .show()

                }
            }
        })
        dialog.show()

    }

    private fun openDialogUpdatePassword() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.bottom_sheet_update_password)
        val pinViewBottomSheetPasswordUpdate =
            dialog.findViewById<PinView>(R.id.pinViewBottomSheetPasswordUpdate)
        val buttonSave = dialog.findViewById<Button>(R.id.buttonSave)

        pinViewBottomSheetPasswordUpdate?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                 if (s?.length == 4) {
                    buttonSave?.setOnClickListener {
                        viewModel.updatePassword(s.toString())
                        dialog.dismiss()
                    }
                }
            }
        })

        viewModel.responseUpdatePassword.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it.data, Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }

}