package tj.tajsoft.loyalrsn.data.remote.repo.auth

import android.annotation.SuppressLint
import android.util.Log
import tj.tajsoft.loyalrsn.data.local.shared_preferance.BeforeNumberStore
import tj.tajsoft.loyalrsn.data.local.shared_preferance.OtpNumber
import tj.tajsoft.loyalrsn.data.local.shared_preferance.PasswordStore
import tj.tajsoft.loyalrsn.data.local.shared_preferance.TokenStore
import tj.tajsoft.loyalrsn.data.local.shared_preferance.TokenUserStore
import tj.tajsoft.loyalrsn.data.local.shared_preferance.UserIdStore
import tj.tajsoft.loyalrsn.data.remote.api.auth.RegisterApi
import tj.tajsoft.loyalrsn.data.remote.model.auth.LogInModel
import tj.tajsoft.loyalrsn.data.remote.model.auth.LogInResponse
import tj.tajsoft.loyalrsn.data.remote.model.auth.RegisterResponse
import tj.tajsoft.loyalrsn.data.remote.model.auth.ResponseFindUsername
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject
class RegisterRepoImpl @Inject constructor(
    private val registerApi: RegisterApi,
    private val userIdStore: UserIdStore,
    private val otpNumber: OtpNumber,
    private val passwordStore: PasswordStore,
    private val tokenStore: TokenStore,
    private val tokenUserStore: TokenUserStore,
    private val beforeNumberStore: BeforeNumberStore
 ) : RegisterRepo {
     override suspend fun register(
        name: String,
        username: String,
        phone_number: String,
        car_number: String,
        email: String,
        birthday: String,
        gender: String,
        password: String,
        city: String
    ): RegisterResponse {
         val pushToken = tokenUserStore.get()!!
         Log.d("TAG", "register: $pushToken")
         val response = registerApi.register(name, username, phone_number, car_number, email, birthday, gender, password, city, pushToken)
         Log.d("TAG", "registeredUserID: $response")
         userIdStore.set(response.userId.toString())
         Log.d("TAG", "registeredUserID: ${response.userId}")
          return response
     }

    override suspend fun saveNumber(number: String) {
        beforeNumberStore.set(number)
     }

    override suspend fun saveNumberFromOtpFragment(number: String) {
        beforeNumberStore.set(number)
    }

    @SuppressLint("SuspiciousIndentation")
    override suspend fun checkPhoneNumber(recipients: String, message: String)
    {
      val response =   registerApi.checkPhoneNumber(recipients,message)
        Log.d("responsePhoneNumber", "checkPhoneNumber:$response ")
    }


    override suspend fun saveOtpNumber(message: String) {
          otpNumber.set(message)
     }

    override suspend fun savePassword(password: String)  = passwordStore.set(password)
    override suspend fun hasPhoneNumber() :String?{
        val beforeNumber = beforeNumberStore.get()
        Log.d("TAG", "hasPhoneNumber: $beforeNumber")
        return  beforeNumber
     }

    override suspend fun hasUserID(): Boolean {
        return userIdStore.get()!=null
    }

    override suspend fun getPassword()  = passwordStore.get()
    override suspend fun clearNumber() {
        beforeNumberStore.clear()
     }

    override suspend fun findUserByUsername(username: String): ResponseFindUsername {
        val response =  registerApi.findUserByUsername(username)
        userIdStore.clear()
        userIdStore.set(response.id.toString())
        Log.d("findUserByUsernameInRegister", "findUserByUsernameInRegister:${userIdStore.get()}")
        return response
    }

    override suspend fun logInCheck(password: String): LogInResponse {
        val userName = beforeNumberStore.get()
        Log.d("logInCheck", "logInCheck: $userName")
        val request = LogInModel("$userName", password)
        val response = registerApi.logInCheck(request)
        tokenStore.set(response.token)
        return response
    }

    override suspend fun saveTokenUser(tokenUser: String) {
        tokenUserStore.set(tokenUser)
    }

    override suspend fun getPhoneNumber():String {
        return beforeNumberStore.get()!!
    }

    private suspend fun saveUserIdToStoreAfterRegister(response:String){
        userIdStore.set(response)
        Log.d("TAG", "saveUserIdToStoreAfterRegister: userid saved ")
    }


}

