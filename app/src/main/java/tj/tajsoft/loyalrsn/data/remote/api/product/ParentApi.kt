package tj.tajsoft.loyalrsn.data.remote.api.product

import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import tj.tajsoft.loyalrsn.data.remote.model.filter_all_users.BodyFilterCorparate
import tj.tajsoft.loyalrsn.data.remote.model.filter_all_users.ResponseTransactionCorparate
import tj.tajsoft.loyalrsn.data.remote.model.parent_user.ResponseUserParent
import tj.tajsoft.loyalrsn.data.remote.model.parent_user.ResponseUserParentItem
import tj.tajsoft.loyalrsn.data.remote.model.parent_user_transaction.ResponseParentTransaction
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction

interface ParentApi {
    @FormUrlEncoded
    @POST("find_user_by_parent")
    suspend fun getParentUsers(
        @Header("Authorization")token: String,
        @Field("parent") parent: String
    ): List<ResponseUserParentItem>

    @FormUrlEncoded
    @POST("find_transactions_parent")
    suspend fun getParentTransaction(
        @Header("Authorization")token: String,
        @Field("parent") parent: Int
    ):List<ResponseParentTransaction>
    @POST("tranthaktion/{userId}")
    suspend fun getTransaction(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int
    ): Array<ResponseTransaction>

    @POST("transactionv_corparate/{userId}")
    suspend fun getTransactionCorparate(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Body bodyFilterCorparate: BodyFilterCorparate
    ): ResponseTransactionCorparate




}