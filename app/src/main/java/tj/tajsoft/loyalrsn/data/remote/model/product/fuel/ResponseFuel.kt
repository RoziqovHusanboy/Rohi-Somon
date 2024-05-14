package tj.tajsoft.loyalrsn.data.remote.model.product.fuel


import com.google.gson.annotations.SerializedName

data class ResponseFuel(
    @SerializedName("data")
    val `data`: List<DataFuel>
)