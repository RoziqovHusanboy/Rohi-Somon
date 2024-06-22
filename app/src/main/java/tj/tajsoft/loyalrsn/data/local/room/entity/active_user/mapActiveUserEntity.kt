package tj.tajsoft.loyalrsn.data.local.room.entity.active_user

import tj.tajsoft.loyalrsn.data.remote.model.product.userActive.Data

fun mapActiveUserEntity(it:Data):ActiveUserEntity{
    return ActiveUserEntity(
        birthday = it.birthday.date,
        carNumber = it.carNumber,
        balans = it.card.balans,
        barcode = it.card.barcode,
        cardType = it.card.cardType,
        card_id = it.card.id,
        card_name = it.card.name,
        card_status = it.card.status,
        city = it.city,
        classX = it.classX,
        email = it.email,
        fond = it.fond,
        gender = it.gender,
        id = it.id,
        name = it.name,
        parent = it.parent,
        phoneNumber = it.phoneNumber,
        pushToken = it.pushToken,
        rId = it.rId,
        saldo = it.saldo,
        status = it.status,
        username = it.username
    )
}