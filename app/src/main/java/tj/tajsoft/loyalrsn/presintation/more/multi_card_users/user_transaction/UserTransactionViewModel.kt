package tj.tajsoft.loyalrsn.presintation.more.multi_card_users.user_transaction

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.remote.model.parent_user_transaction.ResponseParentTransaction
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.domain.repo.ParentRepo
import javax.inject.Inject

@HiltViewModel
class UserTransactionViewModel @Inject constructor(
    private val repo:ParentRepo
) :ViewModel() {
    val parentUserTransaction = MutableLiveData<Array<ResponseTransaction>>()
    val error = MutableLiveData(false)
    val loading = MutableLiveData(false)


       fun getParentUsersTransaction(id:Int) = viewModelScope.launch {
           loading.postValue(true)
           error.postValue(false)
        try {
            error.postValue(false)
            val response = repo.getTransactionUser(id)
            parentUserTransaction.postValue(response)
            Log.d("TAG", "getParentUsersTransaction:$response ")
        } catch (e: Exception) {
            Log.d("TAG", "getParentUsersTransaction: $e")
            error.postValue(true)
        }finally {
            loading.postValue(false)
        }
    }

}