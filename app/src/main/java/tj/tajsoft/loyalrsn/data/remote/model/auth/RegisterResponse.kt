package tj.tajsoft.loyalrsn.data.remote.model.auth

import com.google.gson.annotations.SerializedName

data class RegisterResponse (
    @SerializedName("massage")
    val massage: String,
    @SerializedName("token")
    val userIdStore: Int
)