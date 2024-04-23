package tj.tajsoft.loyalrsn.data.remote.repo.product

import android.util.Log
import kotlinx.coroutines.flow.last
import tj.tajsoft.loyalrsn.data.local.TokenStore
import tj.tajsoft.loyalrsn.data.local.UserIdStore
import tj.tajsoft.loyalrsn.data.remote.api.product.ProductApi
import tj.tajsoft.loyalrsn.data.remote.model.product.ResponseUser
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val userIdStore: UserIdStore,
    private val tokenStore: TokenStore
):ProductRepository {
    override suspend fun getUser(): ResponseUser {
        val userId = userIdStore.get()!!
        val token = tokenStore.get()!!
        Log.d("getUserFromProduct", "getUserFromProduct: ${tokenStore.get()}")
        Log.d("getUserFromProduct", "getUserFromProduct: ${userIdStore.get()}")
        return productApi.getUser("Bearer $token",userId)
    }

}