package tj.tajsoft.loyalrsn.data.remote.model.transactionV2


import com.google.gson.annotations.SerializedName

data class ResponseTransactionV2(
    @SerializedName("status")
    val status: List<Status>,
    @SerializedName("transactionsByDateTime")
    val transactionsByDateTime: List<TransactionsByDateTime>
)