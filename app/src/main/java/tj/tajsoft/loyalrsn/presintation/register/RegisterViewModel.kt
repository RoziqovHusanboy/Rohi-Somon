package tj.tajsoft.loyalrsn.presintation.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.local.BeforeNumberStore
import tj.tajsoft.loyalrsn.data.local.NumberStore
import tj.tajsoft.loyalrsn.data.remote.model.auth.RegisterResponse
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepo: RegisterRepo,
    private val numberStore: NumberStore,
    private val beforeNumberStore: BeforeNumberStore
) :ViewModel() {
      val loading = MutableLiveData(false)
      val error = MutableLiveData<Exception>()
    val response = MutableLiveData<RegisterResponse>()


    fun register(name: String, car_number: String, email: String,
                 birthday: String, gender: String, password: String,city: String) =
        viewModelScope.launch {
            loading.postValue(true)
            try {
                val phone_number = beforeNumberStore.get()
                Log.d("TAG", "register:$phone_number")
                registerRepo.savePassword(password)
               val _response =  registerRepo.register(name, phone_number.toString(), phone_number.toString(), car_number, email, birthday, gender,password,city)
                response.postValue(_response)
             }catch (e:Exception){
                 error.postValue(e)
            }finally {
                loading.postValue(false)
            }
    }

    fun saveNumber() = viewModelScope.launch {
        try {
            beforeNumberStore.get()?.let {
                registerRepo.saveNumber(it)
            }
            Log.d("saveNumber", "saveNumberRegister : saved")

        }catch (e:Exception){
            Log.d("saveNumber", "saveNumber: $e")
        }
    }
}