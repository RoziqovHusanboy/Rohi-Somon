package tj.tajsoft.loyalrsn.data.local

import javax.inject.Inject

class UserIdStore @Inject constructor(): BaseStore<String>("userId",String::class.java)

