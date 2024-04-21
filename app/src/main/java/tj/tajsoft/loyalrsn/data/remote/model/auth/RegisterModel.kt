package tj.tajsoft.loyalrsn.data.remote.model.auth

import com.google.gson.annotations.SerializedName

data class RegisterModel(
    @SerializedName("name")
    val name:String,
    @SerializedName("username")
    val username:String,
    @SerializedName("phone_number")
    val phone_number:String,
    @SerializedName("car_number")
    val car_number:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("birthday")
    val birthday:String,
    @SerializedName("gender")
    val gender:String,
    @SerializedName("city")
    val city:String,
    @SerializedName("password")
    val password:String,
    @SerializedName("push_token")
    val push_token:String
)
