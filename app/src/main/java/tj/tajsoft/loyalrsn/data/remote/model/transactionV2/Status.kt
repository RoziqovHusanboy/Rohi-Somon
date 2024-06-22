package tj.tajsoft.loyalrsn.data.remote.model.transactionV2


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("count")
    val count: Double,
    @SerializedName("current_max")
    val currentMax: Double,
    @SerializedName("left")
    val left: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String
)