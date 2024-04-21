package tj.tajsoft.loyalrsn.data.remote.repo

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.local.NumberStore
import tj.tajsoft.loyalrsn.data.local.OtpNumber
import tj.tajsoft.loyalrsn.data.local.PasswordStore
import tj.tajsoft.loyalrsn.data.local.UserIdStore
import tj.tajsoft.loyalrsn.data.remote.api.auth.RegisterApi
import tj.tajsoft.loyalrsn.data.remote.model.auth.RegisterResponse
import tj.tajsoft.loyalrsn.domain.model.Destination
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

class RegisterRepoImpl @Inject constructor(
    private val registerApi: RegisterApi,
    private val userIdStore: UserIdStore,
    private val numberStore: NumberStore,
    private val otpNumber: OtpNumber,
    private val passwordStore: PasswordStore
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
         saveToStore(response.userIdStore)
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

    override suspend fun destinationFlow() = channelFlow {

        suspend fun sendDestination(){
            when{
                userIdStore.get() !=null && numberStore.get()!=null->send(Destination.LogIn)
                else -> send(Destination.Auth)
            }
            launch {
                userIdStore.getFlow().collectLatest {
                    sendDestination()
                }
                numberStore.getFlow().collectLatest {
                    sendDestination()
                }
            }

        }
    }.distinctUntilChanged()

    override suspend fun hasPhoneNumber() :Boolean{
      return numberStore.get()!=null && passwordStore.get() !=null

    }

    override suspend fun hasUserID(): Boolean {
        return userIdStore.get()!=null
    }

    override suspend fun getPassword()  = passwordStore.get()


    private suspend fun saveToStore(response:Int){
        userIdStore.set(response)
    }


}