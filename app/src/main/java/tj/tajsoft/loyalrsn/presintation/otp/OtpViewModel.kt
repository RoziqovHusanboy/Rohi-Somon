package tj.tajsoft.loyalrsn.presintation.otp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.tajsoft.loyalrsn.data.local.OtpNumber
import tj.tajsoft.loyalrsn.data.remote.model.auth.ResponseFindUsername
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val otpNumber: OtpNumber,
    private val repo: RegisterRepo
) : ViewModel() {
    var numberOtp = String()
    val responseFindUser = MutableLiveData<ResponseFindUsername>()
    val error = MutableLiveData<Exception>()

    init {
        checkOtpNumber()
    }


    fun findUserByUsername(username:String) = viewModelScope.launch{
        try {
            val result = repo.findUserByUsername("+992$username")
            Log.d("TAG", "findUserByUsername:+992$username")
            responseFindUser.postValue(result)
             Log.d("findUserByUsername", "findUserByUsername:${result.id} ")
        }catch (e:Exception){
            Log.d("findUserByUsername", "findUserByUsername:$e")
            error.postValue(e)

        }
    }
    fun saveBeforeNumber(number: String) = viewModelScope.launch {
        withContext(NonCancellable){
        try {
            repo.saveNumberFromOtpFragment("+992$number")
            Log.d("saveNumber", "saveNumber: saved number")
        }catch (e:Exception){
            Log.d("saveNumber", "saveNumberERROR: $e")
        }
        }
    }


    private fun checkOtpNumber() = viewModelScope.launch {
        try {
            val otp = otpNumber.get()
            numberOtp = otp.toString()
            Log.d("TAG", "checkOtpNumber: $otp")
        } catch (e: Exception) {
            Log.d("ExceptionOTP", "ExceptionOTP:$e ")
        }
    }


}