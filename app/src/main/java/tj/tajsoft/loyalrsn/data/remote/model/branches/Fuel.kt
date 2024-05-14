package tj.tajsoft.loyalrsn.data.remote.model.branches


import com.google.gson.annotations.SerializedName

data class Fuel(
    @SerializedName("color")
    val color: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)