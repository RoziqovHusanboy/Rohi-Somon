package tj.tajsoft.loyalrsn.app

import android.app.Application
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (!it.isSuccessful){
                return@addOnCompleteListener
            }
            val token = it.result
            Log.d("TAG", "onCreate:$token")
        }
    }
}