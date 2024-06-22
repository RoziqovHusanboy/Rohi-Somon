package tj.tajsoft.loyalrsn.app

import android.app.Activity
import android.app.Application
import android.os.Handler
import android.os.Looper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {
    private val killAppHandler = Handler(Looper.getMainLooper())
    private val killAppRunnable = Runnable {
        // Kill the application
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(1)
    }
    override fun onCreate() {
        super.onCreate()
        killAppHandler.postDelayed(killAppRunnable, 3600000)
    }

    override fun onTerminate() {
        super.onTerminate()
        // Remove the callback when the application is terminated
        killAppHandler.removeCallbacks(killAppRunnable)
    }

    fun resetKillTimer() {
        // Remove any existing callbacks
        killAppHandler.removeCallbacks(killAppRunnable)
        // Schedule the app to be killed after 1 hour (3600000 milliseconds)
        killAppHandler.postDelayed(killAppRunnable, 3600000)
    }

}