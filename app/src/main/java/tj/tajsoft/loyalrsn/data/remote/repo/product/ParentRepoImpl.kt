package tj.tajsoft.loyalrsn.data.remote.repo.product

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.local.room.entity.userTransaction.mapTransaction
import tj.tajsoft.loyalrsn.data.local.shared_preferance.RIDStore
import tj.tajsoft.loyalrsn.data.local.shared_preferance.TokenStore
import tj.tajsoft.loyalrsn.data.remote.api.product.ParentApi
import tj.tajsoft.loyalrsn.data.remote.model.filter_all_users.BodyFilterCorparate
import tj.tajsoft.loyalrsn.data.remote.model.filter_all_users.ResponseTransactionCorparate
import tj.tajsoft.loyalrsn.data.remote.model.parent_user.ResponseUserParentItem
import tj.tajsoft.loyalrsn.data.remote.model.parent_user_transaction.ResponseParentTransaction
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.domain.repo.ParentRepo
import javax.inject.Inject

class ParentRepoImpl @Inject constructor(
    private val api:ParentApi,
    private val tokenStore: TokenStore,
    private val ridStore: RIDStore
) :ParentRepo {
    override suspend fun getParentUsers(): List<ResponseUserParentItem> {
        val token = tokenStore.get()!!
//        val parentID = ridStore.get()!!
        val response = api.getParentUsers("Bearer $token","50024501")
        Log.d("response", "getParentUsers: $response")
//        Log.d("response", "getParentUsers: $parentID")
        Log.d("response", "getParentUsers: $token")
        return response
    }

    override suspend fun getUsersTransaction():List<ResponseParentTransaction> {
        val token = tokenStore.get()!!
        val parentID = ridStore.get()!!
        val response = api.getParentTransaction("Bearer $token", parentID)
        Log.d("TAG", "getParentUsers: $response")
        return response

    }

    override suspend fun getTransactionUser(id: Int): Array<ResponseTransaction> {
        val token = tokenStore.get()!!
//        val userId = userIdStore.get()!!.toString().toInt()
        val response = api.getTransaction("Bearer $token", id)
        Log.d("getTransactionUser", "getTransactionUser: ${response.toList()}")
        return response
    }

    override suspend fun getTransactionCorparate(bodyFilterCorparate: BodyFilterCorparate): ResponseTransactionCorparate {
        val token = tokenStore.get()!!
        val response = api.getTransactionCorparate("Bearer $token", "50024501",bodyFilterCorparate)
        Log.d("TAG", "getTransactionCorparate:${response.transactionsByDateTime.toList()} ")
        return response
    }
}