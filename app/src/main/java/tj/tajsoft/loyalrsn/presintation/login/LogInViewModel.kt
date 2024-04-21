package tj.tajsoft.loyalrsn.presintation.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repo: RegisterRepo
) : ViewModel() {
val password = MutableLiveData<String>()
    init {
        checkPassword()
    }

    private fun checkPassword() = viewModelScope.launch {
       try {
          password.postValue( repo.getPassword())
       }catch (e:Exception){
           Log.d("ExceptionCheckPassword", "checkPassword:$e ")
       }

    }

}