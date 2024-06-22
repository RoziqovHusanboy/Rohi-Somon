package tj.tajsoft.loyalrsn.data.remote.model.filter_all_users


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("count")
    val count: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("summa")
    val summa: String
)