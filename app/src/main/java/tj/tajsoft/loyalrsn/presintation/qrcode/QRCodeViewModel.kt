package tj.tajsoft.loyalrsn.presintation.qrcode

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.remote.model.product.userActive.ResponseUserActive
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import javax.inject.Inject

@HiltViewModel
class QRCodeViewModel @Inject constructor(
    private val repo: ProductRepository
) : ViewModel() {
    val loading = MutableLiveData(false)
    val error = MutableLiveData(false)
    val product = MutableLiveData<ResponseUserActive>()
    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        loading.postValue(true)
         try {
            val response = repo.getUserWithCard()
             product.postValue(response)
        } catch (e: Exception) {
            Log.d("TAG", "getUserError: $e")
            error.postValue(true)
        } finally {
            loading.postValue(true)
        }
    }

}