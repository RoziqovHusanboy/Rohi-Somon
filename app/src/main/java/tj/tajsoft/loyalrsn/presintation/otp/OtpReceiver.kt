package tj.tajsoft.loyalrsn.presintation.otp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.provider.Telephony
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import com.chaos.view.PinView


class OtpReceiver {
    fun broadCastReceiver(
        pinView: PinView,
        viewModel: OtpViewModel,
        phoneNumber: String,
        navController: NavController,
        context: Context
    ) {
        val br = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
                    messages.forEach {
                        val message = it.messageBody.filter { it.isDigit() } ?: ""
                        try {
                            if (message.length == 4) {
                                pinView.setText(message)
                                viewModel.responseFindUser.observeForever { responseUser ->
                                    if (responseUser.found) {
                                        navController.navigate(
                                            OtpFragmentDirections.toLogInFragment(phoneNumber)
                                        )
                                    } else {
                                        navController.navigate(OtpFragmentDirections.toRegisterOneFragment())
                                    }
                                }
                                viewModel.error.observeForever {
                                    val messageCode =
                                        it.message?.filter { it.isDigit() }?.toInt() ?: 0
                                    if (messageCode == 404) {
                                        navController.navigate(OtpFragmentDirections.toRegisterOneFragment())
                                    }
                                }
                                viewModel.saveBeforeNumber(phoneNumber)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Message code length out of range",
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
        context.registerReceiver(
            br,
            IntentFilter("android.provider.Telephony.SMS_RECEIVED")
        )
    }
}
