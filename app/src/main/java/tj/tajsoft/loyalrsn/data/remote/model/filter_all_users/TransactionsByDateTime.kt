package tj.tajsoft.loyalrsn.data.remote.model.filter_all_users


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
    val payment: Int,
    @SerializedName("payment_type")
    val paymentType: String,
    @SerializedName("user_id")
    val userId: Int
)