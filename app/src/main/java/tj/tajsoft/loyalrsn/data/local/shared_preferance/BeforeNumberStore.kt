package tj.tajsoft.loyalrsn.data.local.shared_preferance

import javax.inject.Inject

class BeforeNumberStore @Inject constructor(): BaseStore<String>("before_number",String::class.java)