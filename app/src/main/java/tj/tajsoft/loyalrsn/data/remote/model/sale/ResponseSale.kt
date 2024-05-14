package tj.tajsoft.loyalrsn.data.remote.model.sale


import com.google.gson.annotations.SerializedName

data class ResponseSale(
    @SerializedName("data")
    val `data`: List<Data>
)