package tj.tajsoft.loyalrsn.data.remote.model.sale


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("deadline")
    val deadline: Deadline,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("sale")
    val sale: String,
    @SerializedName("StartDate")
    val startDate: StartDate,
    @SerializedName("title")
    val title: String
)