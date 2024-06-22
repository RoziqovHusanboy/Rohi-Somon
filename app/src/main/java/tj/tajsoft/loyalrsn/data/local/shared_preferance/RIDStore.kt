package tj.tajsoft.loyalrsn.data.local.shared_preferance

import javax.inject.Inject

class RIDStore @Inject constructor(): BaseStore<Int>("r_id",Int::class.java)

