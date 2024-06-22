package tj.tajsoft.loyalrsn.data.local.room.entity.active_user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activeUserEntity")
data class ActiveUserEntity(
    @PrimaryKey
    val id: Int,
    val birthday: String,
    val carNumber: String,
    val balans: Double,
    val barcode: String,
    val cardType: String,
    val card_id: Int,
    val card_name: String,
    val card_status: Int,
    val city: String,
    val classX: String,
    val email: String,
    val fond: String,
    val gender: Int,
    val name: String,
    val parent: String,
    val phoneNumber: String,
    val pushToken: String,
    val rId: Int,
    val saldo: String,
    val status: String,
    val username: String
)