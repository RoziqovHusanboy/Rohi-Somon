package tj.tajsoft.loyalrsn.presintation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tj.tajsoft.loyalrsn.data.local.room.entity.active_user.ActiveUserEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.fuel.FuelEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.sale.SaleEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.userEntity.UserEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.userTransaction.UserTransactionEntity
import tj.tajsoft.loyalrsn.data.local.shared_preferance.model_cash.TransactionModelCash
import tj.tajsoft.loyalrsn.data.remote.model.transactionV2.ResponseTransactionV2
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ProductRepository
) : ViewModel() {
    val user = MutableLiveData<List<UserEntity>>()
    val transaction = MutableLiveData<List<UserTransactionEntity>>()
    val fuel = MutableLiveData<List<FuelEntity>>()
    val sale = MutableLiveData<List<SaleEntity>>()
    val loading = MutableLiveData(false)
    val saleLoading = MutableLiveData(false)
    val error = MutableLiveData(false)
    val transactionV2Local = MutableLiveData<TransactionModelCash>()

    fun refreshingAllLiveData(onComplete: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    getUser()
                    getSale()
                    getFuel()
                    getTransaction()
                    getTransactionV2()
                    onComplete()
                    Log.d("TAG", "Fetched user from server:")
                } catch (e: Exception) {
                    onComplete()
                    Log.d("TAG", "Error fetching user from server: $e")
                }
            }
        }
    }

    init {
        getTransactionLocal()
        getUserFromLocal()
        getFuelFromLocal()
        getSaleFromLocal()
        getTransactionV2FromLocal()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getTransactionLocal() = viewModelScope.launch {

        withContext(Dispatchers.IO) {
            val response = repo.getTransactionFromLocal()
            response
                .onStart {
                    loading.postValue(true)
                    error.postValue(false)
                }
                .catch {
                    loading.postValue(false)
                    Log.d("TAG", "getTransaction: $it")
                    error.postValue(true)
                }
                .collectLatest {
                    loading.postValue(false)
                    Log.d("user", "getTransactionLocal: $it")
                    if (it.isNullOrEmpty()) {
                        repo.getTransaction()
                    } else {
                        transaction.postValue(it)
                    }
                }
        }
    }

    private fun getTransaction() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repo.getTransaction()
        }
    }

    private fun getUserFromLocal() = viewModelScope.launch {

        withContext(Dispatchers.IO) {
            val response = repo.getUserFromLocal()
            response
                .onStart {
                    loading.postValue(true)
                }
                .catch {
                    loading.postValue(false)
                    Log.d("TAG", "getUserFromLocal: $it")
                    error.postValue(true)
                }
                .collectLatest {
                    loading.postValue(false)
                    if (it.isNullOrEmpty()) {
                        repo.getUser()
                    } else {
                        user.postValue(it)
                    }
                    Log.d("userr", "getUserFromLocalVM: $it")
                }
        }

    }

    private fun getUser() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repo.getUser()
        }
    }


    private fun getFuelFromLocal() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val response = repo.getFuelFromLocal()
            response
                .onStart {
                    loading.postValue(true)
                    error.postValue(false)
                }
                .catch {
                    loading.postValue(false)
                    error.postValue(true)
                    Log.d("user", "getFuelFromLocal:$it ")
                }
                .collectLatest {
                    if (it.isNullOrEmpty()) repo.getFuel()
                    loading.postValue(false)
                    Log.d("user", "getFuelFromLocalVM: $it")
                    fuel.postValue(it)
                }
        }
    }

    private fun getFuel() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repo.getFuel()
        }
    }

    private fun getSaleFromLocal() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val response = repo.getSaleFromLocal()
            response
                .onStart {
                    saleLoading.postValue(true)
                    error.postValue(false)
                }
                .catch {
                    saleLoading.postValue(false)
                    error.postValue(true)
                    Log.d("TAG", "getSaleError: $it")
                }
                .collectLatest {
                    saleLoading.postValue(false)
                    if (it.isNullOrEmpty()) {
                        repo.getSale()
                    } else {
                        sale.postValue(it)
                    }
                    Log.d("user", "getSaleFromLocalVM: $it")
                }

        }

    }

    private fun getSale() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repo.getSale()
        }
    }

    private fun getTransactionV2() = viewModelScope.launch {
        try {
            val response = repo.getTransactionV2()
            Log.d("user", "getTransactionV2: $response")
        } catch (e: Exception) {
            Log.d("user", "getTransactionV2: $e")
        }
    }

    private fun getTransactionV2FromLocal() = viewModelScope.launch {
        try {
            val response = repo.getTransactionV2FromLocal()
            Log.d("user", "getTransactionV2FromLocal:$response ")
            response.collectLatest {
                if (it == null) {
                    repo.getTransactionV2()
                    Log.d("user", "getTransactionV2FromLocal:$it ")
                } else {
                    transactionV2Local.postValue(it)
                    Log.d("user", "getTransactionV2FromLocal:$it ")

                }
            }
        } catch (e: Exception) {
            Log.d("user", "getTransactionV2FromLocal: $e")
        }

    }
}