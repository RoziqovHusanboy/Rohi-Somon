package tj.tajsoft.loyalrsn.presintation.notification

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val repo: ProductRepository
) : ViewModel() {
    val error = MutableLiveData(false)
    val badge = MutableLiveData<Int>()

    init {
        getUser()
    }

    fun updateBudge() = viewModelScope.launch {
        withContext(NonCancellable) {
            try {
                val response = repo.updateBudge()
                Log.d("TAG", "updateBudge:$response ")
            } catch (e: Exception) {
                Log.d("TAG", "updateBudgeError: $e")
            }
        }
    }


    fun getUser() = viewModelScope.launch {
        withContext(NonCancellable) {
            try {
                var count = 0
                val response = repo.getUserFromLocal()
                response.collectLatest {
                    it.forEach {
                        val pushBadge = it.pushBadge?.toInt()
                        pushBadge?.let {
                            count += it
                        }
                    }
                    badge.postValue(count)
                }
                Log.d("TAG", "getUser:$response ")
            } catch (e: Exception) {
                Log.d("TAG", "getUserError: $e")
            }
        }
    }


}