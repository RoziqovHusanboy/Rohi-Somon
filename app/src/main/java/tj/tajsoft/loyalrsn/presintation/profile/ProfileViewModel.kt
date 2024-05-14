package tj.tajsoft.loyalrsn.presintation.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.remote.model.product.user.ResponseUser
import tj.tajsoft.loyalrsn.data.remote.model.updateCarNumber.ResponseCarNumber
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: ProductRepository
):ViewModel() {
    val user = MutableLiveData<ResponseUser>()
    val responseCarNumber = MutableLiveData<ResponseCarNumber>()
    val loading = MutableLiveData(false)
    val error = MutableLiveData(false)

    init {
        getUser()
    }
      fun getUser() = viewModelScope.launch {
        loading.postValue(true)
        try {
            val response = repo.getUser()
            user.postValue(response)
        } catch (e: Exception) {
            loading.postValue(false)
            Log.d("TAG", "getUserError: $e")
            error.postValue(true)
        } finally {
            loading.postValue(true)
        }
    }

    fun updateCarNumber(carNumber:String) = viewModelScope.launch {
        try {
           val response =  repo.updateCarNumber(carNumber)
            responseCarNumber.postValue(response)
            Log.d("TAG", "updateCarNumber:${repo.updateCarNumber(carNumber)} ")
        }catch (e:Exception){
            error.postValue(true)
            Log.d("TAG", "updateCarNumber: $e")
        }
    }


}