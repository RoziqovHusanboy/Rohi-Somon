package tj.tajsoft.loyalrsn.data.local.room.entity.fuel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "fuelEntity")
data class FuelEntity(
     @PrimaryKey
     val id:Int,
     val backgroundcolor: String,
     val cashback: String,
     val code: String,
     val name: String,
     val price: Double
)
