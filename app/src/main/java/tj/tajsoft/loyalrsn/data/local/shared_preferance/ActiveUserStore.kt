package tj.tajsoft.loyalrsn.data.local.shared_preferance

import tj.tajsoft.loyalrsn.data.remote.model.product.userActive.ResponseUserActive
import javax.inject.Inject

class ActiveUserStore @Inject constructor(): BaseStore<Array<ResponseUserActive>>("ActiveUserStore",
    Array<ResponseUserActive>::class.java)