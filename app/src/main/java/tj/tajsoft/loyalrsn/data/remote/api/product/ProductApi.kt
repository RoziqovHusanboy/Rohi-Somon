package tj.tajsoft.loyalrsn.data.remote.api.product

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import tj.tajsoft.loyalrsn.data.remote.model.product.ResponseUser

interface ProductApi {
     @GET("user/{userId}")
    suspend fun getUser(
        @Header("Authorization")token:String,
        @Path("userId")userId:Int
    ):ResponseUser

}