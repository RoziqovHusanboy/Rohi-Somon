package tj.tajsoft.loyalrsn.data.local

import javax.inject.Inject

class TokenUserStore @Inject constructor(): BaseStore<String>("token_user",String::class.java)

