package tj.tajsoft.loyalrsn.data.local.shared_preferance

import javax.inject.Inject

class NumberStore @Inject constructor(): BaseStore<String>("number",String::class.java)