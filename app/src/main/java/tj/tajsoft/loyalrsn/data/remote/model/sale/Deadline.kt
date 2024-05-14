package tj.tajsoft.loyalrsn.data.remote.model.sale


import com.google.gson.annotations.SerializedName

data class Deadline(
    @SerializedName("date")
    val date: String,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_type")
    val timezoneType: Int
)