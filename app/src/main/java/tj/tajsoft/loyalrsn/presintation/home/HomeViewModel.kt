package tj.tajsoft.loyalrsn.presintation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.remote.model.product.ResponseUser
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
 import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo:ProductRepository
):ViewModel() {
    val product = MutableLiveData<ResponseUser>()
    val loading = MutableLiveData(false)
    val error = MutableLiveData(false)
    init {
        getUser()
    }

      fun getUser()= viewModelScope.launch {
        loading.postValue(true)
        try {
            val response = repo.getUser()
            Log.d("TAG", "getUser:${response.data.name} ")
        product.postValue(response)
        }catch (e:Exception){
            Log.d("TAG", "getUserError: $e")
            error.postValue(true)
        }finally {
            loading.postValue(true)
        }
    }

}