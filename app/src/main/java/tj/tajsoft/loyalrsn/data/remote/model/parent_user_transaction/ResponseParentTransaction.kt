package tj.tajsoft.loyalrsn.data.remote.model.parent_user_transaction


import com.google.gson.annotations.SerializedName

data class ResponseParentTransaction(
    @SerializedName("totals")
    val totals: List<Any>,
    @SerializedName("transactionsByDateTime")
    val transactionsByDateTime: List<Any>,
    @SerializedName("user")
    val user: User
)