package tj.tajsoft.loyalrsn.data.local.room.entity.fuel

import tj.tajsoft.loyalrsn.data.remote.model.product.fuel.DataFuel

fun mapFuelEntity(it:DataFuel):FuelEntity {
    return FuelEntity(
        backgroundcolor = it.backgroundcolor,
        cashback = it.cashback,
        code = it.code,
        name = it.name,
        price = it.price,
        id = it.id
    )
}