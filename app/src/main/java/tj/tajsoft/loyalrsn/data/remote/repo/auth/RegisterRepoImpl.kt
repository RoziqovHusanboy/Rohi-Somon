package tj.tajsoft.loyalrsn.data.remote.repo.auth

import android.util.Log
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import retrofit2.Call
import tj.tajsoft.loyalrsn.data.local.NumberStore
import tj.tajsoft.loyalrsn.data.local.OtpNumber
import tj.tajsoft.loyalrsn.data.local.PasswordStore
import tj.tajsoft.loyalrsn.data.local.TokenStore
import tj.tajsoft.loyalrsn.data.local.UserIdStore
import tj.tajsoft.loyalrsn.data.remote.api.auth.RegisterApi
import tj.tajsoft.loyalrsn.data.remote.model.auth.LogInModel
import tj.tajsoft.loyalrsn.data.remote.model.auth.LogInResponse
import tj.tajsoft.loyalrsn.data.remote.model.auth.RegisterResponse
import tj.tajsoft.loyalrsn.data.remote.model.auth.ResponseFindUsername
import tj.tajsoft.loyalrsn.data.remote.model.product.ResponseUser
import tj.tajsoft.loyalrsn.domain.model.Destination
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject
class RegisterRepoImpl @Inject constructor(
    private val registerApi: RegisterApi,
    private val userIdStore: UserIdStore,
    private val numberStore: NumberStore,
    private val otpNumber: OtpNumber,
    private val passwordStore: PasswordStore,
    private val tokenStore: TokenStore
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
        city: String,
        push_token: String
    ): RegisterResponse {
         val response = registerApi.register(name, username, phone_number, car_number, email, birthday, gender, password, city, push_token)
         saveUserIdToStoreAfterRegister(response.userIdStore)
          return response
     }

    override suspend fun saveNumber(number: Int) {
        numberStore.set(number)
        Log.d("saveNumber", "saveNumber:${numberStore.get()}")
    }

    override suspend fun checkPhoneNumber(recipients: String, message: String)  = registerApi.checkPhoneNumber(recipients,message)

    override suspend fun saveOtpNumber(message: String) {
          otpNumber.set(message)
        Log.d("saveOtpNumber", "saveOtpNumber: ${otpNumber.get()}")
    }

    override suspend fun savePassword(password: String)  = passwordStore.set(password)
    override suspend fun hasPhoneNumber() :Boolean{
      return (numberStore.get()!=null)
    }

    override suspend fun hasUserID(): Boolean {
        return userIdStore.get()!=null
    }

    override suspend fun getPassword()  = passwordStore.get()
    override suspend fun clearNumber() {
        numberStore.clear()
        Log.d("Clear", "clearNumber: ${numberStore.get()}")
    }

    override suspend fun findUserByUsername(username: String): ResponseFindUsername {
        val response =  registerApi.findUserByUsername(username)
        userIdStore.set(response.id)
        Log.d("findUserByUsernameInRegister", "findUserByUsernameInRegister:${userIdStore.get()}")
        return response
    }

    override suspend fun logInCheck(password: String): LogInResponse {
        val userName = numberStore.get()
        val request = LogInModel("+992$userName", password)
        val response = registerApi.logInCheck(request)
        tokenStore.set(response.token)
        return response
    }

    override suspend fun getUser(): Call<ResponseUser> {
        val userId = userIdStore.get()!!
        val token = tokenStore.get()!!
        Log.d("getUserFromProduct", "getUserFromProduct: ${tokenStore.get()}")
        Log.d("getUserFromProduct", "getUserFromProduct: ${userIdStore.get()}")
        val response = registerApi.getUser("Bearer $token",userId)
        return response
    }


    private suspend fun saveUserIdToStoreAfterRegister(response:Int){
        userIdStore.set(response)
    }


}

