package tj.tajsoft.loyalrsn.data.remote.model.product.fuel


import com.google.gson.annotations.SerializedName

data class DataFuel(
    @SerializedName("backgroundcolor")
    val backgroundcolor: String,
    @SerializedName("cashback")
    val cashback: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double
)