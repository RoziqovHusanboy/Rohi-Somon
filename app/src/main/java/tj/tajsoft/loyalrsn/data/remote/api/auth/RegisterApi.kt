package tj.tajsoft.loyalrsn.data.remote.api.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import tj.tajsoft.loyalrsn.data.remote.model.auth.LogInModel
import tj.tajsoft.loyalrsn.data.remote.model.auth.LogInResponse
import tj.tajsoft.loyalrsn.data.remote.model.auth.RegisterResponse
import tj.tajsoft.loyalrsn.data.remote.model.auth.ResponseFindUsername
import tj.tajsoft.loyalrsn.data.remote.model.product.ResponseUser

interface RegisterApi {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("phone_number") phone_number: String,
        @Field("car_number") car_number: String,
        @Field("email") email: String,
        @Field("birthday") birthday: String,
        @Field("gender") gender: String,
        @Field("password") password: String,
        @Field("city") city: String,
        @Field("push_token") push_token: String
    ): RegisterResponse


    @FormUrlEncoded
    @POST("oson_sms")
    suspend fun checkPhoneNumber(
        @Field("recipients") recipients: String,
        @Field("message") message: String
    )
    @FormUrlEncoded
    @POST("find_user_by_username")
    suspend fun findUserByUsername(@Field("username") username: String):ResponseFindUsername

    @POST("login_check")
    suspend fun logInCheck(@Body logInModel: LogInModel):LogInResponse

    @GET("user/{userId}")
    suspend fun getUser(
        @Header("Authorization")token:String,
        @Path("userId")userId:Int
    ):Call< ResponseUser>

}