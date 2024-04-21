package tj.tajsoft.loyalrsn.presintation.check_number

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.local.NumberStore
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class CheckNumberViewModel @Inject constructor(
     private val registerRepo: RegisterRepo
) : ViewModel() {
    val error= MutableLiveData(false)

    fun saveNumber(number: Int) = viewModelScope.launch {
        try {
            registerRepo.saveNumber(number)
        }catch (e:Exception){
            Log.d("saveNumber", "saveNumber: $e")
        }
    }

    fun checkPhoneNumber(recipients: String) = viewModelScope.launch {
        val randomNumber = Random().nextInt(9000)+1000
        val randomNumberToString = randomNumber.toString()
        Log.d("randomNumber", "randomNumber: $randomNumber")
        try {
            registerRepo.saveOtpNumber(randomNumberToString)
            registerRepo.checkPhoneNumber(recipients,randomNumberToString)
        }catch (e:Exception){
            error.postValue(true)
        }
    }


}