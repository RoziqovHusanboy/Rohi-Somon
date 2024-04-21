package tj.tajsoft.loyalrsn.data.remote.api.auth

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import tj.tajsoft.loyalrsn.data.remote.model.auth.RegisterResponse

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


}