package tj.tajsoft.loyalrsn.domain.model

import tj.tajsoft.loyalrsn.data.remote.model.parent_user.ResponseUserParentItem
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction

data class ModelResponseParent (
    val parentUser:List<ResponseUserParentItem>?=null,
    val parentUserTransaction:List<ResponseTransaction>?=null
)