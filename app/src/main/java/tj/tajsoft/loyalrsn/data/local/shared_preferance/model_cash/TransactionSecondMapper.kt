package tj.tajsoft.loyalrsn.data.local.shared_preferance.model_cash

import tj.tajsoft.loyalrsn.data.remote.model.transactionV2.ResponseTransactionV2

fun transactionSecondMapper(response:ResponseTransactionV2):TransactionModelCash {
        return TransactionModelCash(
            status = statusModelCashMapper(response.status),
            transactionsByDateTime = transactionByTimeModelCashMapper(response.transactionsByDateTime)
        )
}