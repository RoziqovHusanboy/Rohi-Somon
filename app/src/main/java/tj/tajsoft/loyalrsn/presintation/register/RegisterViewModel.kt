package tj.tajsoft.loyalrsn.presintation.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.local.NumberStore
import tj.tajsoft.loyalrsn.data.remote.model.auth.RegisterResponse
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepo: RegisterRepo,
    private val numberStore: NumberStore
) :ViewModel() {
      val loading = MutableLiveData(false)
      val error = MutableLiveData<Exception>()
    val response = MutableLiveData<RegisterResponse>()


    fun register(name: String, car_number: String, email: String,
                 birthday: String, gender: String, password: String,city: String, push_token: String) =
        viewModelScope.launch {
            loading.postValue(true)
            try {
                val phone_number = numberStore.get()
                registerRepo.savePassword(password)
                Log.d("phone_number", "register:${numberStore.get()} ")
               val _response =  registerRepo.register(name, phone_number.toString(), phone_number.toString(), car_number, email, birthday, gender,password,city, push_token)
                response.postValue(_response)
             }catch (e:Exception){
                 error.postValue(e)
            }finally {
                loading.postValue(false)
            }
    }


}