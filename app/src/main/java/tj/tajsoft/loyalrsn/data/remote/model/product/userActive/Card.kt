package tj.tajsoft.loyalrsn.data.remote.model.product.userActive


import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("balans")
    val balans: Double,
    @SerializedName("barcode")
    val barcode: String,
    @SerializedName("card_type")
    val cardType: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: Int
)