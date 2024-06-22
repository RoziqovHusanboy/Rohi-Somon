package tj.tajsoft.loyalrsn.data.remote.model.parent_user_transaction


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("r_id")
    val rId: Int
)