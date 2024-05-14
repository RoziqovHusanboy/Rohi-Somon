package tj.tajsoft.loyalrsn.data.remote.model.product.transaction


import com.google.gson.annotations.SerializedName

data class ResponseTransaction(
    @SerializedName("address")
    val address: String,
    @SerializedName("branch")
    val branch: String,
    @SerializedName("cashback")
    val cashback: String,
    @SerializedName("create_add")
    val createAdd: CreateAdd,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("payment")
    val payment: Double,
    @SerializedName("payment_type")
    val paymentType: String
)