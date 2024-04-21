package tj.tajsoft.loyalrsn.domain.repo

import tj.tajsoft.loyalrsn.data.remote.model.auth.RegisterResponse

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

}