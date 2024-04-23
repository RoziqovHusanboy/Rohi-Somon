package tj.tajsoft.loyalrsn.data.remote.model.auth

import com.google.gson.annotations.SerializedName

data class LogInResponse(
    @SerializedName("token")
    val token:String
)