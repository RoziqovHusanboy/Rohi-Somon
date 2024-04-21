package tj.tajsoft.loyalrsn.domain.repo

import kotlinx.coroutines.flow.Flow
import tj.tajsoft.loyalrsn.data.remote.model.auth.RegisterResponse
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

    suspend fun destinationFlow(): Flow<Destination>

    suspend fun hasPhoneNumber():Boolean
    suspend fun hasUserID():Boolean

    suspend fun getPassword(): String?

}