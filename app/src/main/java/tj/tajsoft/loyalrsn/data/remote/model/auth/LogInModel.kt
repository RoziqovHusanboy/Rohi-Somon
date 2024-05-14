package tj.tajsoft.loyalrsn.data.remote.model.auth

import com.google.gson.annotations.SerializedName

data class LogInModel(
    @SerializedName("username")
    val username:String,
    @SerializedName("password")
    val password:String
)