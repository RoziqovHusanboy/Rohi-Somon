package tj.tajsoft.loyalrsn.data.remote.model.transactionV2


import com.google.gson.annotations.SerializedName

data class TransactionsByDateTime(
    @SerializedName("address")
    val address: String,
    @SerializedName("branch")
    val branch: String,
    @SerializedName("cashback")
    val cashback: String,
    @SerializedName("create_add")
    val createAdd: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("payment")
    val payment: Double,
    @SerializedName("payment_type")
    val paymentType: String
)