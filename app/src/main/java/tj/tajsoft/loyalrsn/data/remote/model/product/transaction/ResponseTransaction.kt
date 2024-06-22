package tj.tajsoft.loyalrsn.data.remote.model.product.transaction


import com.google.gson.annotations.SerializedName

data class ResponseTransaction(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("branch")
    val branch: String,
    @SerializedName("cashback")
    val cashback: String,
    @SerializedName("create_add")
    val createAdd: CreateAdd?=null,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("payment")
    val payment: Double,
    @SerializedName("payment_type")
    val paymentType: String
)