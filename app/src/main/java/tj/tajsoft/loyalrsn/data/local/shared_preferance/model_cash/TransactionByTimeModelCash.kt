package tj.tajsoft.loyalrsn.data.local.shared_preferance.model_cash

data class TransactionByTimeModelCash (
     val address: String,
     val branch: String,
     val cashback: String,
     val createAdd: String,
     val items: List<ItemTransactionModelCash>,
     val payment: Double,
     val paymentType: String
)