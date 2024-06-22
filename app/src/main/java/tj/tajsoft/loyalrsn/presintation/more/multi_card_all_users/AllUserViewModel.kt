package tj.tajsoft.loyalrsn.presintation.more.multi_card_all_users

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.annotations.SerializedName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tj.tajsoft.loyalrsn.data.remote.model.filter_all_users.BodyFilterCorparate
import tj.tajsoft.loyalrsn.data.remote.model.filter_all_users.ResponseTransactionCorparate
import tj.tajsoft.loyalrsn.data.remote.model.parent_user.ResponseUserParentItem
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.CreateAdd
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.Item
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.domain.model.ModelResponseParent
import tj.tajsoft.loyalrsn.domain.repo.ParentRepo
import javax.inject.Inject

@HiltViewModel
class AllUserViewModel @Inject constructor(
    private val repo: ParentRepo
) : ViewModel() {
    val parent = MutableLiveData<List<ModelResponseParent>>()
    val filterTransaction = MutableLiveData<ResponseTransactionCorparate>()
    val error = MutableLiveData(false)
    val loading = MutableLiveData(false)

    init {
        getParentUsers()
    }

    private fun getParentUsers() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            withContext(NonCancellable) {
                loading.postValue(true)
                error.postValue(false)
                try {
                    val response = repo.getParentUsers()
                    parent.postValue(arrayListOf(ModelResponseParent(parentUser = response)))
                    Log.d("response", "getParentUsers:$response ")
                } catch (e: Exception) {
                    Log.d("response", "getParentUsers: $e")
                    loading.postValue(false)
                    error.postValue(true)
                } finally {
                    loading.postValue(false)
                }
            }
        }
    }

    fun getParentUsersTransaction(id: Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            withContext(NonCancellable) {
                loading.postValue(true)
                error.postValue(false)
                try {
                    error.postValue(false)
                    val response = repo.getTransactionUser(id)
                    parent.postValue(
                        arrayListOf(ModelResponseParent(parentUserTransaction = response.toList()))
                    )
//                    parentUserTransactions.postValue(response)
                    Log.d(
                        "getParentUsersTransaction",
                        "getParentUsersTransaction:${response} "
                    )
                } catch (e: Exception) {
                    Log.d("getParentUsersTransaction", "getParentUsersTransaction: $e")
                    error.postValue(true)
                } finally {
                    loading.postValue(false)
                }
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun getTransactionCorparate(startDate: String, endDate: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            withContext(NonCancellable) {
                loading.postValue(true)
                error.postValue(false)
                try {
                    error.postValue(false)
                    val response = repo.getTransactionCorparate(
                        bodyFilterCorparate = BodyFilterCorparate(startDate, endDate))

                    response.transactionsByDateTime.map {
                     val mapResponse =    ResponseTransaction(
                            userId = it.userId.toString(),
                            address = it.address,
                            branch = it.branch,
                            cashback = it.cashback,
                            items = arrayListOf(Item(count = it.items.last().count, name = it.items.last().name, summa = it.items.last().summa)),
                            paymentType = it.paymentType,
                            payment = it.payment.toDouble()
                        )
                        parent.postValue(arrayListOf(ModelResponseParent(parentUserTransaction = arrayListOf(mapResponse))))
                    }
//                    filterTransaction.postValue(response)
                } catch (e: Exception) {
                    Log.d("getParentUsersTransaction", "getParentUsersTransaction: $e")
                    error.postValue(true)
                } finally {
                    loading.postValue(false)
                }
            }
        }
    }

}

