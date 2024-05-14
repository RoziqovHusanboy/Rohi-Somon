package tj.tajsoft.loyalrsn.app

import android.app.Application
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.local.TokenUserStore
import javax.inject.Inject

@HiltAndroidApp
class App:Application() {

    override fun onCreate() {
        super.onCreate()

    }
}