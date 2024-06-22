package tj.tajsoft.loyalrsn.presintation.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tj.tajsoft.loyalrsn.data.local.shared_preferance.BeforeNumberStore
import tj.tajsoft.loyalrsn.data.local.shared_preferance.OtpNumber
import tj.tajsoft.loyalrsn.data.remote.model.auth.LogInResponse
import tj.tajsoft.loyalrsn.data.remote.model.updataPassword.ResponsePassword
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repo: RegisterRepo,
    private val otpNumber: OtpNumber,
    private val repoProduct: ProductRepository,
    private val beforeNumberStore: BeforeNumberStore
) : ViewModel() {
    var numberOtp = String()
    val responseLogIn = MutableLiveData<LogInResponse>()
    val phoneNumber = MutableLiveData<String>()
    val responseUpdatePassword = MutableLiveData<ResponsePassword>()
    val loading = MutableLiveData(false)
    val error = MutableLiveData(false)

    init {
        getPhoneNumber()
    }

    fun checkLogIn(password: String) = viewModelScope.launch {
        loading.postValue(true)
        error.postValue(false)
        try {
            val response = repo.logInCheck(password)
            responseLogIn.postValue(response)
        } catch (e: Exception) {
            loading.postValue(false)
            error.postValue(true)
            Log.d("ExceptionCheckPassword", "checkPassword:$e ")
        } finally {
            loading.postValue(false)
        }
    }



    fun updatePassword(password: String) = viewModelScope.launch {
        try {
            val response = repoProduct.updatePassword(password)
            responseUpdatePassword.postValue(response)
            Log.d("updatePassword", "updatePassword: $response")
        } catch (e: Exception) {
            Log.d("updatePassword", "updatePassword: $e")
        }
    }

    private fun getPhoneNumber() = viewModelScope.launch {
        try {
            val response = repo.getPhoneNumber()
            phoneNumber.postValue(response)
            Log.d("getPhoneNumber", "getPhoneNumber: $response")
        } catch (e: Exception) {
            Log.d("getPhoneNumber", "getPhoneNumber: $e")
        }
    }



    fun checkPhoneNumber( ) = viewModelScope.launch {
        val recipients = repo.getPhoneNumber()
        val splitRecipients = recipients.substring(4,13)
        val randomNumber = Random().nextInt(9000) + 1000
        numberOtp = randomNumber.toString()
        val randomNumberToString = randomNumber.toString()
        withContext(NonCancellable) {
            try {
                Log.d("checkPhoneNumber", "numberOtpsplitRecipients: $splitRecipients")
                 repo.checkPhoneNumber(splitRecipients, randomNumberToString)
                Log.d("checkPhoneNumber", "checkPhoneNumber: checkedPhoneNumber")
                Log.d("checkPhoneNumber", "numberOtp: $numberOtp")

            } catch (e: Exception) {
                error.postValue(true)
                Log.d("checkPhoneNumberError", "checkPhoneNumber:$e ")
            }
        }
    }
}