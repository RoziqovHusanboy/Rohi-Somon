package tj.tajsoft.loyalrsn.data.remote.model.branches


import com.google.gson.annotations.SerializedName

data class Service(
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("name")
    val name: String
)