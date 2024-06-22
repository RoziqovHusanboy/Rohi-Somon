package tj.tajsoft.loyalrsn.data.remote.model.parent_user


import com.google.gson.annotations.SerializedName

data class Cards(
    @SerializedName("balans")
    val balans: Double,
    @SerializedName("barcode")
    val barcode: String,
    @SerializedName("card_type")
    val cardType: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)