package tj.tajsoft.loyalrsn.data.local.shared_preferance

import javax.inject.Inject

class UserIdStore @Inject constructor(): BaseStore<String>("userId",String::class.java)

