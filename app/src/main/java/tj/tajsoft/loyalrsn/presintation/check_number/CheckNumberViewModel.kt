package tj.tajsoft.loyalrsn.presintation.check_number

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class CheckNumberViewModel @Inject constructor(
    private val registerRepo: RegisterRepo
) : ViewModel() {
    val error = MutableLiveData(false)


    fun checkPhoneNumber(recipients: String) = viewModelScope.launch {
        val randomNumber = Random().nextInt(9000) + 1000
        val randomNumberToString = randomNumber.toString()
        withContext(NonCancellable) {
            try {
                registerRepo.saveOtpNumber(randomNumberToString)
                registerRepo.checkPhoneNumber(recipients, randomNumberToString)
                Log.d("checkPhoneNumber", "checkPhoneNumber: checkedPhoneNumber")


            } catch (e: Exception) {
                error.postValue(true)
                Log.d("checkPhoneNumberError", "checkPhoneNumber:$e ")
            }
        }
    }


}