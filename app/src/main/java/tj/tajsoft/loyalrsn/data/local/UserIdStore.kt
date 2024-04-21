package tj.tajsoft.loyalrsn.data.local

import javax.inject.Inject

class UserIdStore @Inject constructor(): BaseStore<Int>("token",Int::class.java)

