package tj.tajsoft.loyalrsn.data.remote.model.filter_all_users

import com.google.gson.annotations.SerializedName

data class BodyFilterCorparate(
    @SerializedName("start_date")
    val startDate:String,
    @SerializedName("end_date")
    val endDate:String
)
