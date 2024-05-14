package tj.tajsoft.loyalrsn.presintation.discount

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
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
            sale.postValue(repo.getSale())
         }catch (e:Exception){
             loading.postValue(false)
            error.postValue(true)
            Log.d("TAG", "getSaleError: $e")
        }finally {
            loading.postValue(false)
        }
    }

}