package tj.tajsoft.loyalrsn.presintation.discount

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.local.room.entity.sale.SaleEntity
import tj.tajsoft.loyalrsn.data.remote.model.sale.ResponseSale
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import javax.inject.Inject
@HiltViewModel
class DiscountViewModel @Inject constructor(
  private val repo:ProductRepository
) :ViewModel() {
    val loading = MutableLiveData(false)
    val error = MutableLiveData(false)
    val sale = MutableLiveData<ResponseSale>()

    init {
        getSale()
    }

    private fun getSale() = viewModelScope.launch {
        loading.postValue(true)
        error.postValue(false)
        try {
            val response = repo.getSaleFromDiscount()

                Log.d("repo", "getSale: $response")
                sale.postValue(response)

         }catch (e:Exception){
             loading.postValue(false)
            error.postValue(true)
            Log.d("TAG", "getSaleError: $e")
        }finally {
            loading.postValue(false)
        }
    }

}