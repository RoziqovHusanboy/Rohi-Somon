package tj.tajsoft.loyalrsn.data.local.shared_preferance

import javax.inject.Inject

class OtpNumber @Inject constructor (): BaseStore<String>("otpNumber",String::class.java)