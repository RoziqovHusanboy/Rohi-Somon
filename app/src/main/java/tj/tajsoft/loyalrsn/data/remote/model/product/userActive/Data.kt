package tj.tajsoft.loyalrsn.data.remote.model.product.userActive


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("birthday")
    val birthday: Birthday,
    @SerializedName("car_number")
    val carNumber: String,
    @SerializedName("card")
    val card: Card,
    @SerializedName("city")
    val city: String,
    @SerializedName("class")
    val classX: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("fond")
    val fond: String,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("parent")
    val parent: String,
    @SerializedName("PhoneNumber")
    val phoneNumber: String,
    @SerializedName("push_token")
    val pushToken: String,
    @SerializedName("r_id")
    val rId: Int,
    @SerializedName("saldo")
    val saldo: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("username")
    val username: String
)