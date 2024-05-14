package tj.tajsoft.loyalrsn.data.remote.model.updataPassword


import com.google.gson.annotations.SerializedName

data class RequestPassword(
    @SerializedName("password")
    val password: String
)