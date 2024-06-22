package tj.tajsoft.loyalrsn.data.local.room.entity.sale

import tj.tajsoft.loyalrsn.data.remote.model.sale.Data

fun mapSaleEntity(it: Data): SaleEntity {
    return SaleEntity(
        deadline = it.deadline.date,
        description = it.description,
        img = it.img,
        sale = it.sale,
        startDate = it.startDate.date,
        title = it.title,
        id = it.id,

    )

}