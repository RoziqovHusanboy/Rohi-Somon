package tj.tajsoft.loyalrsn.data.remote.model.auth


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("user_id")
    val userId: Int
)