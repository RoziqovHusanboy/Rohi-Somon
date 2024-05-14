package tj.tajsoft.loyalrsn.data.remote.model.product.userActive


import com.google.gson.annotations.SerializedName

data class Birthday(
    @SerializedName("date")
    val date: String,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_type")
    val timezoneType: Int
)