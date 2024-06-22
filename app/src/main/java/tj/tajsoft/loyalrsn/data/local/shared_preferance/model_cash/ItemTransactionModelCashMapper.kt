package tj.tajsoft.loyalrsn.data.local.shared_preferance.model_cash

import tj.tajsoft.loyalrsn.data.remote.model.transactionV2.Item

fun itemTransactionModelCashMapper (item:List<Item>):List<ItemTransactionModelCash>{
    return item.map {
        ItemTransactionModelCash(
            count = it.count,
            name = it.name,
            summa = it.summa
        )
    }
}