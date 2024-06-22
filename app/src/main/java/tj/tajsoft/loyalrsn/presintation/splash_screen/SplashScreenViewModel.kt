package tj.tajsoft.loyalrsn.presintation.splash_screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor (
    private val repo: RegisterRepo
):ViewModel() {
    val hasNumber = MutableLiveData<String?>()
    init {
        hasPhoneNumber()
    }

    private fun hasPhoneNumber() = viewModelScope.launch{
        try {
            val result =  repo.hasPhoneNumber()
            Log.d("TAG", "hasPhoneNumber: $result")
            hasNumber.postValue(result)
         }catch (e:Exception){
            Log.d("ExceptionHasNumber", "hasPhoneNumber:$e")
        }
    }

}