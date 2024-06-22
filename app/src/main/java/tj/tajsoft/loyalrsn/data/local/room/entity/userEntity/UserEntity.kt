package tj.tajsoft.loyalrsn.data.local.room.entity.userEntity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import tj.tajsoft.loyalrsn.data.remote.model.product.user.Birthday
import tj.tajsoft.loyalrsn.data.remote.model.product.user.Card

@Entity(tableName = "userEntity")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val birthday: String,
    val carNumber: String,
    val idCard: Int?,
    val nameCard: String?,
    val cardType: String?,
    val balanceCard: String?,
    val barcodeCard: String?,
    val statusCard: Int?,
    val city: String,
    val classX: String,
    val email: String,
    val fond: Int,
    val gender: Int,
    val name: String,
    val owner: String,
    val phoneNumber: String,
    val pushBadge: String?,
    val pushToken: String,
    val rId: Int,
    val saldo: Int,
    val status: String,
    val username: String
)
