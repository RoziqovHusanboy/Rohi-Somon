package tj.tajsoft.loyalrsn.data.local.shared_preferance.model_cash

import tj.tajsoft.loyalrsn.data.remote.model.transactionV2.TransactionsByDateTime

fun transactionByTimeModelCashMapper(response:List<TransactionsByDateTime>):List<TransactionByTimeModelCash>{
    return response.map {
            TransactionByTimeModelCash(
                address = it.address,
                branch = it.branch,
                cashback = it.cashback,
                createAdd = it.createAdd,
                items = itemTransactionModelCashMapper(it.items),
                payment = it.payment,
                paymentType = it.paymentType
            )
        }


}