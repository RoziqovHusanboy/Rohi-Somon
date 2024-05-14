package tj.tajsoft.loyalrsn.data.local

import javax.inject.Inject

class BeforeNumberStore @Inject constructor(): BaseStore<String>("before_number",String::class.java)