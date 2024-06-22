package tj.tajsoft.loyalrsn.domain.repo

import kotlinx.coroutines.flow.Flow
import tj.tajsoft.loyalrsn.data.local.room.entity.active_user.ActiveUserEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.fuel.FuelEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.sale.SaleEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.userEntity.UserEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.userTransaction.UserTransactionEntity
import tj.tajsoft.loyalrsn.data.local.shared_preferance.model_cash.TransactionModelCash
import tj.tajsoft.loyalrsn.data.remote.model.branches.ResponseBranches
import tj.tajsoft.loyalrsn.data.remote.model.product.fuel.ResponseFuel
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.data.remote.model.product.user.ResponseUser
import tj.tajsoft.loyalrsn.data.remote.model.product.userActive.ResponseUserActive
import tj.tajsoft.loyalrsn.data.remote.model.sale.ResponseSale
import tj.tajsoft.loyalrsn.data.remote.model.transactionV2.ResponseTransactionV2
import tj.tajsoft.loyalrsn.data.remote.model.updataPassword.ResponsePassword
import tj.tajsoft.loyalrsn.data.remote.model.updateCarNumber.ResponseCarNumber

interface ProductRepository {
    suspend fun getUser(): ResponseUser
    suspend fun getUserFromLocal():Flow<List<UserEntity>>
    suspend fun getUserWithCard():ResponseUserActive
    suspend fun getUserWithCardFromLocal():Flow<ActiveUserEntity>
    suspend fun getUserId():Int?
    suspend fun getTransaction(): Array<ResponseTransaction>
    suspend fun getTransactionFromLocal():Flow<List<UserTransactionEntity>>

    suspend fun getTransactionV2():ResponseTransactionV2
    suspend fun getTransactionV2FromLocal():Flow<TransactionModelCash?>
    suspend fun getFuel(): ResponseFuel
    suspend fun getFuelFromLocal():Flow<List<FuelEntity>>
    suspend fun getSale():ResponseSale
    suspend fun getSaleFromLocal():Flow<List<SaleEntity>>
    suspend fun getSaleFromDiscount():ResponseSale
    suspend fun getBranches():ResponseBranches
    suspend fun updateCarNumber(carNumber:String):ResponseCarNumber
    suspend fun updatePassword(password:String):ResponsePassword
    suspend fun clearDataBase()
    suspend fun updateBudge()

}