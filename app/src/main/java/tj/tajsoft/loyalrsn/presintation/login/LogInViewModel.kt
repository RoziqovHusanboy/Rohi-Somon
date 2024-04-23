package tj.tajsoft.loyalrsn.presintation.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.remote.model.auth.LogInResponse
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repo: RegisterRepo
) : ViewModel() {
    val responseLogIn = MutableLiveData<LogInResponse>()
    val loading = MutableLiveData(false)


      fun checkLogIn(password: String) = viewModelScope.launch {
          loading.postValue(true)
        try {
            val response = repo.logInCheck(password)
            responseLogIn.postValue(response)
            Log.d("TAG", "checkLogIn:${response.token} ")
        } catch (e: Exception) {
            Log.d("ExceptionCheckPassword", "checkPassword:$e ")
        }finally {
            loading.postValue(false)
        }
    }

}