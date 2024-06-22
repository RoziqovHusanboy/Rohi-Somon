package tj.tajsoft.loyalrsn.domain.repo

import tj.tajsoft.loyalrsn.data.remote.model.auth.LogInResponse
import tj.tajsoft.loyalrsn.data.remote.model.auth.RegisterResponse
import tj.tajsoft.loyalrsn.data.remote.model.auth.ResponseFindUsername

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
        city: String
     ): RegisterResponse

    suspend fun saveNumber(number:String)
    suspend fun saveNumberFromOtpFragment(number: String)
    suspend fun checkPhoneNumber(recipients:String,message:String)
    suspend fun saveOtpNumber(message: String)
    suspend fun savePassword(password: String)
    suspend fun hasPhoneNumber():String?
    suspend fun hasUserID():Boolean
    suspend fun getPassword(): String?
    suspend fun clearNumber()
    suspend fun findUserByUsername(username: String):ResponseFindUsername
    suspend fun logInCheck(password: String):LogInResponse
    suspend fun saveTokenUser(tokenUser:String)
    suspend fun getPhoneNumber():String



}