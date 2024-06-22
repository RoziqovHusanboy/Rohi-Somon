package tj.tajsoft.loyalrsn.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tj.tajsoft.loyalrsn.data.remote.repo.auth.RegisterRepoImpl
import tj.tajsoft.loyalrsn.data.remote.repo.product.ParentRepoImpl
import tj.tajsoft.loyalrsn.data.remote.repo.product.ProductRepositoryImpl
import tj.tajsoft.loyalrsn.domain.repo.ParentRepo
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRegisterRepository(
        registerRepoImpl: RegisterRepoImpl
    ): RegisterRepo

    @Binds
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ):ProductRepository

    @Binds
    abstract fun bindParentRepository(
         parentRepoImpl: ParentRepoImpl
    ):ParentRepo

}