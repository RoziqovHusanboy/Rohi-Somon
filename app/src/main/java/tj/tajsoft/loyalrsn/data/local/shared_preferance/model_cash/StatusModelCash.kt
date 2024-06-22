package tj.tajsoft.loyalrsn.data.local.shared_preferance.model_cash

import com.google.gson.annotations.SerializedName

data class StatusModelCash(
    val count: Double,
    val currentMax: Double,
    val left: Double,
    val name: String,
    val status: String
)