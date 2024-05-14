package tj.tajsoft.loyalrsn.domain.repo

import tj.tajsoft.loyalrsn.data.remote.model.branches.ResponseBranches
import tj.tajsoft.loyalrsn.data.remote.model.product.fuel.ResponseFuel
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.data.remote.model.product.user.ResponseUser
import tj.tajsoft.loyalrsn.data.remote.model.product.userActive.ResponseUserActive
import tj.tajsoft.loyalrsn.data.remote.model.sale.ResponseSale
import tj.tajsoft.loyalrsn.data.remote.model.updataPassword.ResponsePassword
import tj.tajsoft.loyalrsn.data.remote.model.updateCarNumber.ResponseCarNumber

interface ProductRepository {
    suspend fun getUser(): ResponseUser
    suspend fun getUserWithCard(): ResponseUserActive
    suspend fun getUserId():Int?
    suspend fun getTransaction(): ArrayList<ResponseTransaction>
    suspend fun getFuel(): ResponseFuel
    suspend fun getSale():ResponseSale
    suspend fun getBranches():ResponseBranches
    suspend fun updateCarNumber(carNumber:String):ResponseCarNumber
    suspend fun updatePassword(password:String):ResponsePassword

}