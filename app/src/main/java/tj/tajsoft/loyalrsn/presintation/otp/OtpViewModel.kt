package tj.tajsoft.loyalrsn.presintation.otp

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
 import tj.tajsoft.loyalrsn.data.local.OtpNumber
import tj.tajsoft.loyalrsn.data.remote.model.auth.ResponseFindUsername
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val otpNumber: OtpNumber,
    private val repo: RegisterRepo
) : ViewModel() {
    var numberOtp =String()
     val responseFindUser = MutableLiveData<ResponseFindUsername>()

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
            Log.d("findUserByUsername", "findUserByUsername:$e ")
        }
    }
    fun saveNumber(number: Int) = viewModelScope.launch {
        try {
            repo.saveNumber(number)
            Log.d("saveNumber", "saveNumber: saved number")
        }catch (e:Exception){
            Log.d("saveNumber", "saveNumber: $e")
        }
    }



    private fun checkOtpNumber() = viewModelScope.launch {
        try {
            val otp = otpNumber.get()
            numberOtp = otp.toString()
            Log.d("numberOtpViewModel", "checkOtpNumber: $numberOtp")
        }catch (e:Exception){
            Log.d("ExceptionOTP", "ExceptionOTP:$e ")
        }
    }



}