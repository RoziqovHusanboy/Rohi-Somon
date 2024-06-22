package tj.tajsoft.loyalrsn.data.local.shared_preferance

import tj.tajsoft.loyalrsn.data.remote.model.product.user.ResponseUser
import javax.inject.Inject

class ResponseUserStore @Inject constructor(): BaseStore<Array<ResponseUser>>("response_user",Array <ResponseUser>::class.java)