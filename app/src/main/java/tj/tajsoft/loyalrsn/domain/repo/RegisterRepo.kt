package tj.tajsoft.loyalrsn.domain.repo

import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import tj.tajsoft.loyalrsn.data.remote.model.auth.LogInModel
import tj.tajsoft.loyalrsn.data.remote.model.auth.LogInResponse
import tj.tajsoft.loyalrsn.data.remote.model.auth.RegisterResponse
import tj.tajsoft.loyalrsn.data.remote.model.auth.ResponseFindUsername
import tj.tajsoft.loyalrsn.data.remote.model.product.ResponseUser
import tj.tajsoft.loyalrsn.domain.model.Destination

interface RegisterRepo {
    suspend fun register(
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
    ): RegisterResponse

    suspend fun saveNumber(number:Int)
    suspend fun checkPhoneNumber(recipients:String,message:String)
    suspend fun saveOtpNumber(message: String)
    suspend fun savePassword(password: String)
    suspend fun hasPhoneNumber():Boolean
    suspend fun hasUserID():Boolean
    suspend fun getPassword(): String?
    suspend fun clearNumber()
    suspend fun findUserByUsername(username: String):ResponseFindUsername
    suspend fun logInCheck(password: String):LogInResponse

    suspend fun getUser(): Call<ResponseUser>



}