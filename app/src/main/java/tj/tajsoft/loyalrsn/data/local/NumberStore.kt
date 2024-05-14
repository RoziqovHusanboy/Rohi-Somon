package tj.tajsoft.loyalrsn.data.local

import javax.inject.Inject

class NumberStore @Inject constructor(): BaseStore<String>("number",String::class.java)