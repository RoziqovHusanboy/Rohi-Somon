package tj.tajsoft.loyalrsn.data.remote.repo.product

import android.annotation.SuppressLint
import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tj.tajsoft.loyalrsn.data.local.room.dao.HomeDao
import tj.tajsoft.loyalrsn.data.local.room.entity.active_user.mapActiveUserEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.fuel.mapFuelEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.sale.mapSaleEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.userEntity.mapUserEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.userTransaction.mapTransaction
import tj.tajsoft.loyalrsn.data.local.shared_preferance.BeforeNumberStore
import tj.tajsoft.loyalrsn.data.local.shared_preferance.CardIdStore
import tj.tajsoft.loyalrsn.data.local.shared_preferance.TokenStore
import tj.tajsoft.loyalrsn.data.local.shared_preferance.TransactionSecondStore
import tj.tajsoft.loyalrsn.data.local.shared_preferance.UserIdStore
import tj.tajsoft.loyalrsn.data.local.shared_preferance.model_cash.TransactionModelCash
import tj.tajsoft.loyalrsn.data.local.shared_preferance.model_cash.transactionSecondMapper
import tj.tajsoft.loyalrsn.data.remote.api.product.ProductApi
import tj.tajsoft.loyalrsn.data.remote.model.branches.ResponseBranches
import tj.tajsoft.loyalrsn.data.remote.model.product.fuel.ResponseFuel
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.data.remote.model.product.user.ResponseUser
import tj.tajsoft.loyalrsn.data.remote.model.product.userActive.ResponseUserActive
import tj.tajsoft.loyalrsn.data.remote.model.sale.ResponseSale
import tj.tajsoft.loyalrsn.data.remote.model.transactionV2.ResponseTransactionV2
import tj.tajsoft.loyalrsn.data.remote.model.updataPassword.RequestPassword
import tj.tajsoft.loyalrsn.data.remote.model.updataPassword.ResponsePassword
import tj.tajsoft.loyalrsn.data.remote.model.updateCarNumber.RequestCarNumber
import tj.tajsoft.loyalrsn.data.remote.model.updateCarNumber.ResponseCarNumber
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val userIdStore: UserIdStore,
    private val tokenStore: TokenStore,
    private val homeDao: HomeDao,
    private val numberStore: BeforeNumberStore,
    private val cardIdStore: CardIdStore,
    private val transactionSecondStore: TransactionSecondStore
) : ProductRepository {
    val TAG = "store"

    override suspend fun getUser(): ResponseUser {
        val userId = numberStore.get().toString()
        val token = tokenStore.get()!!
        Log.d(TAG, "getUser: $userId")
        val response = productApi.getUser("Bearer $token", userId)
        val newUser = response.data.map { mapUserEntity(it) }
        val existingUser = homeDao.getUser().firstOrNull()
        if (existingUser == null) {
            homeDao.insertUser(newUser)
            Log.d(TAG, "getUser: true")
        }
        if (existingUser != newUser) {
            homeDao.clearUser()
            homeDao.insertUser(newUser)
            Log.d(TAG, "getUserexistingUser: true")
        }
        response.data.forEach {
            if (cardIdStore.get() == null) {
                cardIdStore.set(arrayOf(it.id.toString()))
            } else {
                cardIdStore.clear()
                cardIdStore.set(arrayOf(it.id.toString()))
            }
        }

        return response
    }

    override suspend fun getUserFromLocal() = homeDao.getUser()


    override suspend fun getUserWithCard(): ResponseUserActive {
        val userId = userIdStore.get()!!.toString().toInt()
        val token = tokenStore.get()!!
        val response = productApi.getUserWithCard("Bearer $token", userId)
        val newUserActive = mapActiveUserEntity(response.data)
        val existingUserActive = homeDao.getUserActive().firstOrNull()
        Log.d("user", "getUserWithCard:${response.data} ")
        if (existingUserActive == null) {
            homeDao.insertUserActive(newUserActive)
        }
        if (existingUserActive != newUserActive) {
            homeDao.clearUserActive()
            homeDao.insertUserActive(newUserActive)
        }
        return response
    }

    override suspend fun getUserWithCardFromLocal() = homeDao.getUserActive()


    override suspend fun getUserId(): Int? {
        val user = userIdStore.get().toString().toInt()
        return user
    }

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("SuspiciousIndentation")
    override suspend fun getTransaction(): Array<ResponseTransaction> {
        val token = tokenStore.get()!!
        val userId = userIdStore.get()!!.toString().toInt()
        val response = productApi.getTransaction("Bearer $token", 20287)
        val newtransaction = response.map { mapTransaction(it) }

        GlobalScope.launch(Dispatchers.IO) {
            val existingTransaction = homeDao.getTransaction().firstOrNull()
            if (existingTransaction == null) {
                val inserted = homeDao.insertTransaction(newtransaction)
                Log.d(TAG, "getTransaction: $inserted")
            }
            if (existingTransaction != newtransaction) {
                homeDao.clearTransaction()
                homeDao.insertTransaction(newtransaction)
            }
        }
            return response

    }

    override suspend fun getTransactionFromLocal() = homeDao.getTransaction()
    override suspend fun getTransactionV2(): ResponseTransactionV2 {
        val token = tokenStore.get()!!
        val userId = userIdStore.get()!!.toString().toInt()
//        val cardId = cardIdStore.get()?.first()!!
        Log.d(TAG, "getTransaction: $userId")
        val response = productApi.getTransactionV2("Bearer $token",20287 )
        val newtransaction = transactionSecondMapper(response)
        val existingTransaction = transactionSecondStore.get()
        if (existingTransaction == null) {
            transactionSecondStore.set(newtransaction)
            Log.d(TAG, "getTransactionV2:  set ")
        } else {
            transactionSecondStore.clear()
            transactionSecondStore.set(newtransaction)
        }
//        if (existingTransaction != newtransaction) {
//            transactionSecondStore.clear()
//            transactionSecondStore.set(newtransaction)
//            Log.d(TAG, "getTransactionV2: get")
//        }

        Log.d(TAG, "getTransactionV2: $response")
        //+992884040011
        return response
    }

    override suspend fun getTransactionV2FromLocal(): Flow<TransactionModelCash?> {
        return transactionSecondStore.getFlow()
    }


    override suspend fun getFuel(): ResponseFuel {
        val token = tokenStore.get()!!
        val response = productApi.getFuel("Bearer $token")
        Log.d("user", "getFuelRepo:${response.data} ")
        val fuelEntity = response.data.map { mapFuelEntity(it) }
        val existingFuel = homeDao.getFuel().firstOrNull()
        if (existingFuel.isNullOrEmpty()) {
            homeDao.insertFuel(fuelEntity)
        }
        if (existingFuel != fuelEntity) {
            homeDao.clearFuel()
            homeDao.insertFuel(fuelEntity)
        }
        return response
    }

    override suspend fun getFuelFromLocal() = homeDao.getFuel()


    @SuppressLint("SuspiciousIndentation")
    override suspend fun getSale(): ResponseSale {
        val token = tokenStore.get()!!
        val response = productApi.getSale("Bearer $token")
        Log.d("user", "getSaleRepo: ${response.data}")
        val saleEntities = response.data.map { mapSaleEntity(it) }
        val existingSale = homeDao.getSale().firstOrNull()
        if (existingSale.isNullOrEmpty()) {
            homeDao.insertSale(saleEntities)
        }
        if (existingSale != saleEntities) {
            homeDao.clearSale()
            homeDao.insertSale(saleEntities)
        }
        return response
    }

    override suspend fun getSaleFromLocal() = homeDao.getSale()
    override suspend fun getSaleFromDiscount(): ResponseSale {
        val token = tokenStore.get()!!
        val response = productApi.getSale("Bearer $token")
        return response
    }

    override suspend fun getBranches(): ResponseBranches {
        val token = tokenStore.get()!!
        val response = productApi.getBranches("Bearer $token")
        return response
    }

    override suspend fun updateCarNumber(carNumber: String): ResponseCarNumber {
        val token = tokenStore.get()!!
        val userId = userIdStore.get()!!.toString().toInt()
        val response = productApi.updateCarNumber(
            token = "Bearer $token",
            userId = userId,
            requestCarNumber = RequestCarNumber(carNumber)
        )
        Log.d("TAG", "updateCarNumber: $response")
        return response
    }

    override suspend fun updatePassword(password: String): ResponsePassword {
        val token = tokenStore.get()!!
        val userId = userIdStore.get()!!.toString().toInt()
        val response = productApi.updatePasswordNumber(
            token = "Bearer $token",
            userId = userId,
            requestPassword = RequestPassword(password)
        )
        Log.d("TAG", "updateCarNumber: $response")
        return response
    }

    override suspend fun clearDataBase() {
        withContext(Dispatchers.IO) {
            homeDao.clearUser()
            homeDao.clearSale()
            homeDao.clearFuel()
            homeDao.clearTransaction()
            userIdStore.clear()
            numberStore.clear()
            cardIdStore.clear()
            transactionSecondStore.clear()
        }

    }

    @SuppressLint("SuspiciousIndentation")
    override suspend fun updateBudge() {
        val token = tokenStore.get()!!
        homeDao.getUser().collectLatest {
            it.forEach {
                productApi.updateBudge("Bearer $token", it.id, 0)
            }
        }
    }

}


