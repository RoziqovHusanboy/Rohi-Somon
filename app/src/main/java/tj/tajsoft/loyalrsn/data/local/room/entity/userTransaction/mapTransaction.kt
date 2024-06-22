package tj.tajsoft.loyalrsn.data.local.room.entity.userTransaction

import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction

fun mapTransaction(it:ResponseTransaction):UserTransactionEntity{
    return UserTransactionEntity(
        address = it.address,
        branch = it.branch,
        cashback = it.cashback,
        createAdd = it.createAdd!!.date,
        count = it.items.last().count,
        name = it.items.last().name,
        summa = it.items.last().summa,
        payment = it.payment,
        paymentType = it.paymentType
    )
}