package tj.tajsoft.loyalrsn.data.remote.model.branches


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("address")
    val address: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("fuels")
    val fuels: List<Fuel>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("services")
    val services: List<Service>,
    @SerializedName("title")
    val title: String
)