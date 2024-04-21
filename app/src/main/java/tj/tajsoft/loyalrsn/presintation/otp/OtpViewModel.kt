package tj.tajsoft.loyalrsn.presintation.otp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
 import tj.tajsoft.loyalrsn.data.local.OtpNumber
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val otpNumber: OtpNumber,
    private val repo: RegisterRepo
) : ViewModel() {
    var numberOtp =String()
    val hasUserId = MutableLiveData<Boolean>(false)

    init {
        checkOtpNumber()
        hasUserId()
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

    private fun hasUserId() = viewModelScope.launch{
        try {
            val result = repo.hasUserID()
            hasUserId.postValue(result)
        }catch (e:Exception){
            Log.d("ExceptionHasUserId", "hasUserId:$e ")
        }

    }

}