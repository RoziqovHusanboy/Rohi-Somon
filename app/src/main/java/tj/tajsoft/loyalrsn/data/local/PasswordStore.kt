package tj.tajsoft.loyalrsn.data.local

import javax.inject.Inject

class PasswordStore @Inject constructor():BaseStore<String>("password",String::class.java)