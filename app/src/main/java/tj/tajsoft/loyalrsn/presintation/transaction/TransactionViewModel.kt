package tj.tajsoft.loyalrsn.presintation.transaction

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repo: ProductRepository
) : ViewModel() {
    val transaction = MutableLiveData<Array<ResponseTransaction>>()
    val error = MutableLiveData(false)
    val loading = MutableLiveData(false)

    init {
        getTransaction()
    }

    private fun getTransaction() = viewModelScope.launch {
        with(Dispatchers.IO) {
            with(NonCancellable) {
                loading.postValue(true)
                error.postValue(false)
                try {
                    val response = repo.getTransaction()
                    Log.d("getTransaction", "getTransaction:$response ")
                    transaction.postValue(response)
                } catch (e: Exception) {
                    Log.d("TAG", "getTransaction:$e")
                    error.postValue(true)
                } finally {
                    loading.postValue(false)
                }
            }
        }
    }
}