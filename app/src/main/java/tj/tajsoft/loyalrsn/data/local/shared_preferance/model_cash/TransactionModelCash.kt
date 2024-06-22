package tj.tajsoft.loyalrsn.data.local.shared_preferance.model_cash


data class TransactionModelCash(
     val status: List<StatusModelCash>,
     val transactionsByDateTime: List<TransactionByTimeModelCash>
)
