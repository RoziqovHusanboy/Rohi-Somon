package tj.tajsoft.loyalrsn.data.remote.model.auth


import com.google.gson.annotations.SerializedName

data class ResponseFindUsername(
    @SerializedName("found")
    val found: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("status")
    val status: String
)