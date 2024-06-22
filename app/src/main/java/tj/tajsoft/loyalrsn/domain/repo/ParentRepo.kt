package tj.tajsoft.loyalrsn.domain.repo

import tj.tajsoft.loyalrsn.data.remote.model.filter_all_users.BodyFilterCorparate
import tj.tajsoft.loyalrsn.data.remote.model.filter_all_users.ResponseTransactionCorparate
import tj.tajsoft.loyalrsn.data.remote.model.parent_user.ResponseUserParent
import tj.tajsoft.loyalrsn.data.remote.model.parent_user.ResponseUserParentItem
import tj.tajsoft.loyalrsn.data.remote.model.parent_user_transaction.ResponseParentTransaction
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction

interface ParentRepo {
    suspend fun getParentUsers(): List<ResponseUserParentItem>
    suspend fun getUsersTransaction():List<ResponseParentTransaction>
    suspend fun getTransactionUser(id:Int):Array<ResponseTransaction>
    suspend fun getTransactionCorparate(bodyFilterCorparate: BodyFilterCorparate): ResponseTransactionCorparate
}