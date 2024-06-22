package tj.tajsoft.loyalrsn.data.remote.model.filter_all_users


import com.google.gson.annotations.SerializedName

data class ResponseTransactionCorparate(
    @SerializedName("transactionsByDateTime")
    val transactionsByDateTime: List<TransactionsByDateTime>
)