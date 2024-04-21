package tajsoft.demoproject.myapplication.sharedPreference

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri

class SharedPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences? =
        context.getSharedPreferences("SharedPreference", Context.MODE_PRIVATE)

    fun save(key: String, value: String) {
        val editor = sharedPreferences?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    fun get(key: String): String {
        return sharedPreferences?.getString(key, "") ?: ""
    }
}