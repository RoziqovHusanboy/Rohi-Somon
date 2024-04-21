package tj.tajsoft.loyalrsn.presintation.otp

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
                android.Manifest.permission.RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS),
                111
            )
        } else {
            receiveMsg()
        }

        checkOtpNumber()

    }

    private fun checkOtpNumber() {
        val numberOtp = viewModel.numberOtp
        binding.pinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {

                if (s?.length == 4 && numberOtp == s.toString()) {
                    viewModel.hasUserId.observe(viewLifecycleOwner){
                        if (it == false){
                            findNavController().navigate(OtpFragmentDirections.toRegisterOneFragment())
                        }else{
                            findNavController().navigate(OtpFragmentDirections.toLogInFragment())
                        }
                    }

                }

                if (s?.length == 4 && numberOtp != s.toString()) {
                    context?.getColor(R.color.red)?.let { it1 -> binding.pinView.setLineColor(it1) }
                    binding.pinView.setTextColor(requireContext().getColor(R.color.red))
                    Toast.makeText(requireContext(), "Не провильно ввели код", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun receiveMsg() {
        val br = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)

                    messages.forEach {
                        val massage = it.messageBody.filter { it.isDigit() } ?: ""
                        try {
                            if (massage.length == 4) {
                                binding.pinView.setText(massage)
                                viewModel.hasUserId.observe(viewLifecycleOwner) {
                                    if (it == false) {
                                        findNavController().navigate(OtpFragmentDirections.toRegisterOneFragment())
                                    } else {
                                        findNavController().navigate(OtpFragmentDirections.toLogInFragment())
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "massage code length out of range",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } catch (e: Exception) {
                            Log.d("TAG", "onReceive: $e")
                        }
                    }

                }
            }
        }
        requireContext().registerReceiver(
            br,
            IntentFilter("android.provider.Telephony.SMS_RECEIVED")
        )
    }
}

