package tj.tajsoft.loyalrsn.presintation.otp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.FragmentOtpBinding

@AndroidEntryPoint
class OtpFragment : Fragment() {
    private lateinit var binding: FragmentOtpBinding
    private val viewModel by viewModels<OtpViewModel>()
    private var phoneNumber: String? = null
    private var checkPinview: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            phoneNumber = it!!.getString("number")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS),
                111
            )

        } else {
            OtpReceiver().broadCastReceiver(
                binding.pinView,
                viewModel,
                phoneNumber!!,
                findNavController(),
                requireContext()
            )
        }
        viewModel.findUserByUsername(phoneNumber!!)
        checkOtpNumber()

    }


    private fun checkOtpNumber() {
        binding.pinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val numberOtp = viewModel.numberOtp

                if (s?.length == 4 &&  s.toString() == numberOtp) {
                    viewModel.responseFindUser.observe(viewLifecycleOwner) { responseUser ->
                        if (responseUser.found) {
                            findNavController().navigate(
                                OtpFragmentDirections.toLogInFragment(
                                    phoneNumber!!
                                )
                            )
                        } else {
                            findNavController().navigate(OtpFragmentDirections.toRegisterOneFragment())
                        }
                    }
                    viewModel.error.observe(viewLifecycleOwner) {
                        val massageCode = it.message.toString()?.let {
                            it.filter { it.isDigit() }.toInt()
                        }
                        if (massageCode == 404 && numberOtp == s.toString()) {
                            findNavController().navigate(OtpFragmentDirections.toRegisterOneFragment())
                        }
                    }
                    viewModel.saveBeforeNumber(phoneNumber!!)
                }

                if (s?.length == 4 && numberOtp != s.toString()) {
                    context?.getColor(R.color.red)?.let { it1 -> binding.pinView.setLineColor(it1) }
                    binding.pinView.setTextColor(requireContext().getColor(R.color.red))
                    Toast.makeText(requireContext(), "Не провильно ввели код", Toast.LENGTH_SHORT)
                        .show()
                    checkPinview = false
                }
            }
        })
    }

}

