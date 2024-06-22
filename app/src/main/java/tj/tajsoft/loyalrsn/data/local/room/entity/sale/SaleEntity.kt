package tj.tajsoft.loyalrsn.data.local.room.entity.sale

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import tj.tajsoft.loyalrsn.data.remote.model.sale.Deadline
import tj.tajsoft.loyalrsn.data.remote.model.sale.StartDate

@Entity(tableName = "saleEntity")
data class SaleEntity(
    @PrimaryKey
    val id: Int,
    val deadline: String,
    val description: String,
    val img: String,
    val sale: String,
    val startDate: String,
    val title: String
 )