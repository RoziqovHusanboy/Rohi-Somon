package tj.tajsoft.loyalrsn.data.remote.model.product.user


import com.google.gson.annotations.SerializedName

data class ResponseUser(
    @SerializedName("data")
    val `data`: Data
)