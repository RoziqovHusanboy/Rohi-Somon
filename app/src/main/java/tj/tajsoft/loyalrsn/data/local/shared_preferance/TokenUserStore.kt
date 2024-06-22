package tj.tajsoft.loyalrsn.data.local.shared_preferance

import javax.inject.Inject

class TokenUserStore @Inject constructor(): BaseStore<String>("token_user",String::class.java)

