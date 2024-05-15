package tj.tajsoft.loyalrsn.presintation.more

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.remote.model.product.user.ResponseUser
import tj.tajsoft.loyalrsn.data.remote.model.product.userActive.ResponseUserActive
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val repo: RegisterRepo,
    private val repoProductRepository: ProductRepository
) : ViewModel() {
    val user = MutableLiveData<ResponseUser>()
    val userActive = MutableLiveData<ResponseUserActive>()
    val error = MutableLiveData(false)

    init {
        getUser()
    }

    fun clearNumber() = viewModelScope.launch {
        try {
            repo.clearNumber()
        } catch (e: Exception) {
            Log.d("clearNumber", "clearNumber: $e")
        }
    }

    private fun getUser() = viewModelScope.launch {
        try {
            val response = repoProductRepository.getUserFromLocal()
            user.postValue(response)
        } catch (e: Exception) {
            Log.d("TAG", "getUserError: $e")
            error.postValue(true)
        }
    }

    fun getUserWithCard() = viewModelScope.launch {
        try {
            val response = repoProductRepository.getUserWithCard()
            userActive.postValue(response)
            Log.d("TAG", "getUserWithCard: $response")
        } catch (e: Exception) {
            Log.d("TAG", "getUserErrorCard: $e")
            error.postValue(true)
        }
    }

}