package tj.tajsoft.loyalrsn.domain.repo

import tj.tajsoft.loyalrsn.data.remote.model.product.ResponseUser

interface ProductRepository {
    suspend fun getUser():ResponseUser
}