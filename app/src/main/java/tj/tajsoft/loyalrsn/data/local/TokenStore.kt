package tj.tajsoft.loyalrsn.data.local

import javax.inject.Inject

class TokenStore @Inject constructor():BaseStore<String>("token",String::class.java)