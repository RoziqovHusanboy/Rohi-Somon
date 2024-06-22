package tj.tajsoft.loyalrsn.data.local.room.entity.userEntity

import tj.tajsoft.loyalrsn.data.remote.model.product.user.Data

fun mapUserEntity(it: Data):UserEntity {
    return UserEntity(
        birthday = it.birthday.date,
        carNumber =it.carNumber,
        city = it.city,
        classX = it.classX,
        email = it.email,
        fond =it.fond,
        gender = it.gender,
        name = it.name,
        owner = it.owner,
        phoneNumber =it.phoneNumber,
        pushToken = it.pushToken,
        rId =  it.rId,
        saldo =  it.saldo,
        status = it.status,
        username = it.username,
        id = it.id,
        pushBadge = it.pushBadge,
        idCard = it.card?.id,
        nameCard = it.card?.name,
        cardType = it.card?.cardsType,
        balanceCard = it.card?.balans.toString(),
        barcodeCard = it.card?.barcode.toString(),
        statusCard =  it.card?.status
     )
}