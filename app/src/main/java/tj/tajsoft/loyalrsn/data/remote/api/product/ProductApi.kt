package tj.tajsoft.loyalrsn.data.remote.api.product

import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import tj.tajsoft.loyalrsn.data.remote.model.branches.ResponseBranches
import tj.tajsoft.loyalrsn.data.remote.model.product.fuel.ResponseFuel
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.data.remote.model.product.user.ResponseUser
import tj.tajsoft.loyalrsn.data.remote.model.product.userActive.ResponseUserActive
import tj.tajsoft.loyalrsn.data.remote.model.sale.ResponseSale
import tj.tajsoft.loyalrsn.data.remote.model.transactionV2.ResponseTransactionV2
import tj.tajsoft.loyalrsn.data.remote.model.updataPassword.RequestPassword
import tj.tajsoft.loyalrsn.data.remote.model.updataPassword.ResponsePassword
import tj.tajsoft.loyalrsn.data.remote.model.updateCarNumber.RequestCarNumber
import tj.tajsoft.loyalrsn.data.remote.model.updateCarNumber.ResponseCarNumber

interface ProductApi {
    @GET("userv2/{userId}")
    suspend fun getUser(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): ResponseUser

    @GET("user/{userId}")
    suspend fun getUserWithCard(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int
    ): ResponseUserActive
     @GET("transactionv2/{id}")
    suspend fun getTransactionV2(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ):ResponseTransactionV2

    @POST("tranthaktion/{userId}")
    suspend fun getTransaction(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int
    ): Array<ResponseTransaction>

    @GET("fuel")
    suspend fun getFuel(
        @Header("Authorization") token: String
    ): ResponseFuel

    @GET("sale")
    suspend fun getSale(
        @Header("Authorization") token: String
    ): ResponseSale

    @GET("branches")
    suspend fun getBranches(
        @Header("Authorization") token: String
    ): ResponseBranches

    @POST("user/update/{userId}")
    suspend fun updateCarNumber(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int,
        @Body requestCarNumber: RequestCarNumber
    ): ResponseCarNumber

    @POST("user/update/{userId}")
    suspend fun updatePasswordNumber(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int,
        @Body requestPassword: RequestPassword
    ): ResponsePassword

    @PATCH("user/update/{id_card}")
    suspend fun updateBudge(
        @Header("Authorization") token: String,
        @Path("id_card") id_card: Int,
        @Body push_b:Int)
}