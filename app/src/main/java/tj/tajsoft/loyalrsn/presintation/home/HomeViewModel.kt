package tj.tajsoft.loyalrsn.presintation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.local.BeforeNumberStore
import tj.tajsoft.loyalrsn.data.remote.model.product.fuel.ResponseFuel
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.data.remote.model.product.user.Card
import tj.tajsoft.loyalrsn.data.remote.model.product.user.ResponseUser
import tj.tajsoft.loyalrsn.data.remote.model.product.user.ResponseWithCard
import tj.tajsoft.loyalrsn.data.remote.model.product.userActive.ResponseUserActive
import tj.tajsoft.loyalrsn.data.remote.model.sale.ResponseSale
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ProductRepository,
    private val repoRegisterRepo: RegisterRepo,
    private val beforeNumberStore: BeforeNumberStore
) : ViewModel() {
    val user = MutableLiveData<ResponseUser>()
    val userActive = MutableLiveData<ResponseUserActive>()
    val transaction = MutableLiveData<List<ResponseTransaction>>(null)
    val fuel = MutableLiveData<ResponseFuel>(null)
    val sale = MutableLiveData<ResponseSale>()
    val loading = MutableLiveData(false)
    val Saleloading = MutableLiveData(false)
    val error = MutableLiveData(false)



    init {
        viewModelScope.async {
            getUser().await()
            getTransaction().await()
            getFuel().await()
            getSale().await()

        }
    }

    fun refreshingAllLiveData() = viewModelScope.async {
        getUser().await()
        getTransaction().await()
        getFuel().await()
        getSale().await()
    }

    private fun getTransaction() = viewModelScope.async {
        loading.postValue(true)
        error.postValue(false)
        try {
            val response = repo.getTransaction()
            transaction.postValue(response)
        } catch (e: Exception) {
            loading.postValue(false)
            Log.d("TAG", "getTransaction: $e")
            error.postValue(true)
        } finally {
            loading.postValue(false)
        }

    }

    private fun getUser() = viewModelScope.async {
        loading.postValue(true)
        try {
            val response = repo.getUser()
            user.postValue(response)
        } catch (e: Exception) {
            loading.postValue(false)
            Log.d("TAG", "getUserError: $e")
            error.postValue(true)
        } finally {
            loading.postValue(false)
        }
    }

      fun getUserWithCard() = viewModelScope.launch {
         try {
            val response = repo.getUserWithCard()
            userActive.postValue(response)
            Log.d("TAG", "getUserWithCard: $response")
        } catch (e: Exception) {
             Log.d("TAG", "getUserErrorCard: $e")
            error.postValue(true)
        }
    }

    private fun getFuel() = viewModelScope.async {
        loading.postValue(true)
        error.postValue(false)
        try {
            fuel.postValue(repo.getFuel())
        } catch (e: Exception) {
            error.postValue(true)
        } finally {
            loading.postValue(false)
        }
    }

    private fun getSale() = viewModelScope.async {
        Saleloading.postValue(true)
        error.postValue(false)
        try {
            val response = repo.getSale()
            sale.postValue(response)
        } catch (e: Exception) {
            Saleloading.postValue(false)
            error.postValue(true)
            Log.d("TAG", "getSaleError: $e")
        } finally {
            Saleloading.postValue(false)
        }
    }

    fun saveNumber() = viewModelScope.launch {
        try {
            beforeNumberStore.get()?.let {
                repoRegisterRepo.saveNumber(it)
            }
            Log.d("saveNumber", "saveNumberRegister : saved")

        }catch (e:Exception){
            Log.d("saveNumber", "saveNumber: $e")
        }
    }

}