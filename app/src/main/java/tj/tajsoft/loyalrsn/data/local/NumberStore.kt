package tj.tajsoft.loyalrsn.data.local

import javax.inject.Inject

class NumberStore @Inject constructor(): BaseStore<Int>("number",Int::class.java)