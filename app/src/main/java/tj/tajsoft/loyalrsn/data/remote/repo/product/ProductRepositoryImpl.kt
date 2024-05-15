package tj.tajsoft.loyalrsn.data.remote.repo.product

import android.util.Log
import tj.tajsoft.loyalrsn.data.local.ResponseUserStore
import tj.tajsoft.loyalrsn.data.local.TokenStore
import tj.tajsoft.loyalrsn.data.local.UserIdStore
import tj.tajsoft.loyalrsn.data.remote.api.product.ProductApi
import tj.tajsoft.loyalrsn.data.remote.model.branches.ResponseBranches
import tj.tajsoft.loyalrsn.data.remote.model.product.fuel.ResponseFuel
import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import tj.tajsoft.loyalrsn.data.remote.model.product.user.ResponseUser
import tj.tajsoft.loyalrsn.data.remote.model.product.userActive.ResponseUserActive
import tj.tajsoft.loyalrsn.data.remote.model.sale.ResponseSale
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
    private val responseUserStore: ResponseUserStore
) : ProductRepository {
    override suspend fun getUser(): ResponseUser {
        val userId = userIdStore.get().toString().toInt()
        val token = tokenStore.get()!!
        val response = productApi.getUser("Bearer $token", userId)
       val responseLocal =  responseUserStore.set(response)
        Log.d("TAG", "getUser: responseLocal saved")
        return response
    }

    override suspend fun getUserFromLocal(): ResponseUser {
        val response = responseUserStore.get()
        return response!!

    }

    override suspend fun getUserWithCard(): ResponseUserActive {
        val userId = userIdStore.get()!!.toString().toInt()
        val token = tokenStore.get()!!
        val response = productApi.getUserWithCard("Bearer $token", userId)
        return response
    }


    override suspend fun getUserId(): Int? {
        val user = userIdStore.get().toString().toInt()
        return user
    }

    override suspend fun getTransaction(): ArrayList<ResponseTransaction> {
        val token = tokenStore.get()!!
        val userId = userIdStore.get()!!.toString().toInt()
        val response = productApi.getTransaction("Bearer $token", userId)
        return response
    }

    override suspend fun getFuel(): ResponseFuel {
        val token = tokenStore.get()!!
        val response = productApi.getFuel("Bearer $token")
        return response
    }

    override suspend fun getSale(): ResponseSale {
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


}

