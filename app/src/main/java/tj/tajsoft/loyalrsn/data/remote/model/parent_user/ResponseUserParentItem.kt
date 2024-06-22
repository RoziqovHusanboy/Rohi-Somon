package tj.tajsoft.loyalrsn.data.remote.model.parent_user


import com.google.gson.annotations.SerializedName
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction

data class ResponseUserParentItem(
    @SerializedName("employees")
    val employees: List<Employee>,
    @SerializedName("fond")
    val fond: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: String,
    @SerializedName("saldo")
    val saldo: Int,
    @SerializedName("username")
    val username: String
)

