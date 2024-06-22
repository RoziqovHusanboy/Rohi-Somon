package tj.tajsoft.loyalrsn.presintation.more

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.local.room.entity.userEntity.UserEntity
import tj.tajsoft.loyalrsn.data.remote.model.product.user.ResponseUser
import tj.tajsoft.loyalrsn.data.remote.model.product.userActive.ResponseUserActive
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val repo: RegisterRepo,
    private val productRepo: ProductRepository,
 ) : ViewModel() {
    val user = MutableLiveData<List<UserEntity>>()
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
            val response = productRepo.getUserFromLocal()
            response.let {
                it.collectLatest {
                    user.postValue(it)
                }
            }
        } catch (e: Exception) {
            Log.d("TAG", "getUserError: $e")
            error.postValue(true)
        }
    }
    fun clearDataBase() = viewModelScope.launch {
        try {
             productRepo.clearDataBase()
        } catch (e: Exception) {
            Log.d("TAG", "getUserErrorCard: $e")
         }
    }

}