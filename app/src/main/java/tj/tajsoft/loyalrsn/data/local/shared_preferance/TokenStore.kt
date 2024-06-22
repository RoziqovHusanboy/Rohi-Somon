package tj.tajsoft.loyalrsn.data.local.shared_preferance

import javax.inject.Inject

class TokenStore @Inject constructor(): BaseStore<String>("token",String::class.java)