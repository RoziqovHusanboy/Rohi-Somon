package tj.tajsoft.loyalrsn.data.local

import javax.inject.Inject

class OtpNumber @Inject constructor ():BaseStore<String>("otpNumber",String::class.java)