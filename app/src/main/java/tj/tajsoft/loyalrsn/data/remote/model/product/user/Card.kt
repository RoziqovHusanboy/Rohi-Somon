package tj.tajsoft.loyalrsn.data.remote.model.product.user


import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("balans")
    val balans: Double,
    @SerializedName("barcode")
    val barcode: String,
    @SerializedName("cards_type")
    val cardsType: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: Int
)