package tj.tajsoft.loyalrsn.data.local.room.entity.userTransaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.CreateAdd
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.Item

@Entity(tableName = "userTransactionEntity")
data class UserTransactionEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val address: String,
    val branch: String,
    val cashback: String,
    val createAdd: String,
    val count: String,
    val name: String,
    val summa: String,
    val payment: Double,
    val paymentType: String
)
