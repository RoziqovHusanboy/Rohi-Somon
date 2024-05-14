package tj.tajsoft.loyalrsn.presintation.transaction

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repo: ProductRepository
) : ViewModel() {
    val transaction = MutableLiveData<ArrayList<ResponseTransaction>>(null)
    val error = MutableLiveData(false)
    val loading = MutableLiveData(false)

    init {
        getTransaction()
    }

    private fun getTransaction() = viewModelScope.launch {
        loading.postValue(true)
        error.postValue(false)
        try {
            transaction.postValue(repo.getTransaction())
        } catch (e: Exception) {
             error.postValue(true)
        } finally {
            loading.postValue(false)
        }
    }
}