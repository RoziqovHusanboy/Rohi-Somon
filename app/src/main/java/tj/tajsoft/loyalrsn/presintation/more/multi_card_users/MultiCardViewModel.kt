package tj.tajsoft.loyalrsn.presintation.more.multi_card_users

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.remote.model.parent_user.ResponseUserParent
import tj.tajsoft.loyalrsn.data.remote.model.parent_user.ResponseUserParentItem
import tj.tajsoft.loyalrsn.domain.repo.ParentRepo
import javax.inject.Inject

@HiltViewModel
class MultiCardViewModel @Inject constructor(
    private val repo:ParentRepo
) :ViewModel() {
    val parentUser = MutableLiveData<List<ResponseUserParentItem>>()
    val error = MutableLiveData(false)
    val loading = MutableLiveData(false)

    init {
        getParentUsers()
    }

    private fun getParentUsers() = viewModelScope.launch {
        loading.postValue(true)
        error.postValue(false)
        try {
            val response = repo.getParentUsers()
            parentUser.postValue(response)
            Log.d("response", "getParentUsers:$response ")
        } catch (e: Exception) {
            Log.d("response", "getParentUsers: $e")
            loading.postValue(false)
            error.postValue(true)
        }finally {
            loading.postValue(false)
        }
    }
}