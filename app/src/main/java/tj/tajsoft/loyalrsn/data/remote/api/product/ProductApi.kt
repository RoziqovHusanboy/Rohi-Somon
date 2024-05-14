package tj.tajsoft.loyalrsn.data.remote.api.product

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import tj.tajsoft.loyalrsn.data.remote.model.branches.ResponseBranches
import tj.tajsoft.loyalrsn.data.remote.model.product.fuel.ResponseFuel
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.data.remote.model.product.user.ResponseUser
import tj.tajsoft.loyalrsn.data.remote.model.product.userActive.ResponseUserActive
import tj.tajsoft.loyalrsn.data.remote.model.sale.ResponseSale
import tj.tajsoft.loyalrsn.data.remote.model.updataPassword.RequestPassword
import tj.tajsoft.loyalrsn.data.remote.model.updataPassword.ResponsePassword
import tj.tajsoft.loyalrsn.data.remote.model.updateCarNumber.RequestCarNumber
import tj.tajsoft.loyalrsn.data.remote.model.updateCarNumber.ResponseCarNumber

interface ProductApi {
     @GET("user/{userId}")
    suspend fun getUser(
        @Header("Authorization")token:String,
        @Path("userId")userId:Int
    ):  ResponseUser

    @GET("user/{userId}")
    suspend fun getUserWithCard(
        @Header("Authorization")token:String,
        @Path("userId")userId:Int
    ):ResponseUserActive

    @POST("tranthaktion/{userId}")
    suspend fun getTransaction(
        @Header("Authorization")token: String,
        @Path("userId")userId:Int
    ): ArrayList<ResponseTransaction>

    @GET("fuel")
    suspend fun getFuel(
        @Header("Authorization")token: String
    ):ResponseFuel

    @GET("sale")
    suspend fun getSale(
        @Header("Authorization")token: String
    ):ResponseSale

    @GET("branches")
    suspend fun getBranches(
        @Header("Authorization")token: String
    ):ResponseBranches

    @POST("user/update/{userId}")
    suspend fun updateCarNumber(
        @Header("Authorization")token: String,
        @Path("userId")userId:Int,
        @Body requestCarNumber: RequestCarNumber
    ):ResponseCarNumber

    @POST("user/update/{userId}")
    suspend fun updatePasswordNumber(
        @Header("Authorization")token: String,
        @Path("userId")userId:Int,
        @Body requestPassword: RequestPassword
    ):ResponsePassword
}