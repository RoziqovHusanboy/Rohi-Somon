package tj.tajsoft.loyalrsn.data.remote.model.parent_user


import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("cards")
    val cards: Cards,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: String,
    @SerializedName("r_id")
    val rId: Int
)