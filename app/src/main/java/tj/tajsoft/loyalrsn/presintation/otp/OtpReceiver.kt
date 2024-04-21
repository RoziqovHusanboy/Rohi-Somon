package tj.tajsoft.loyalrsn.presintation.otp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log
import android.widget.EditText

class OtpReceiver : BroadcastReceiver() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText

    fun setEditText(
        editText1: EditText,
        editText2: EditText,
        editText3: EditText,
        editText4: EditText
    ) {
        this.editText1 = editText1
        this.editText2 = editText2
        this.editText3 = editText3
        this.editText4 = editText4
    }


    override fun onReceive(context: Context?, intent: Intent?) {
        val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        messages.forEach {

            val message = it.messageBody.filter { it.isDigit() } ?: ""

            try {
                if (message.length == 4) {
                    val m1 = message[0]
                    val m2 = message[1]
                    val m3 = message[2]
                    val m4 = message[3]
                    editText1.setText(m1.toString())
                    editText2.setText(m2.toString())
                    editText3.setText(m3.toString())
                    editText4.setText(m4.toString())
                }

            } catch (e: Exception) {
                Log.d("TAG", "onReceive: $e")
            }
        }
    }
}


