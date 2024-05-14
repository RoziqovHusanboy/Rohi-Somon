package tj.tajsoft.loyalrsn.data.remote.model.product.transaction


import com.google.gson.annotations.SerializedName

data class CreateAdd(
    @SerializedName("date")
    val date: String,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_type")
    val timezoneType: Int
)