package tj.tajsoft.loyalrsn.data.remote.model.branches


import com.google.gson.annotations.SerializedName

data class ResponseBranches(
    @SerializedName("data")
    val `data`: ArrayList<Data>
)