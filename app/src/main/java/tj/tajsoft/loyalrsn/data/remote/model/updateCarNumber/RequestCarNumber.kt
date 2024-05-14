package tj.tajsoft.loyalrsn.data.remote.model.updateCarNumber


import com.google.gson.annotations.SerializedName

data class RequestCarNumber(
    @SerializedName("car_number")
    val carNumber: String
)