package tj.tajsoft.loyalrsn.data.local

import tj.tajsoft.loyalrsn.data.remote.model.product.user.ResponseUser
import javax.inject.Inject

class ResponseUserStore @Inject constructor():BaseStore<ResponseUser>("response_user",ResponseUser::class.java)