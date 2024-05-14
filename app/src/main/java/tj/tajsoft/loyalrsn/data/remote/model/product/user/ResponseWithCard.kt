package tj.tajsoft.loyalrsn.data.remote.model.product.user


import com.google.gson.annotations.SerializedName

data class ResponseWithCard(
    @SerializedName("card")
    val card: Card?
)