package tj.tajsoft.loyalrsn.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tj.tajsoft.loyalrsn.data.remote.repo.RegisterRepoImpl
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRegisterRepository(
        registerRepoImpl: RegisterRepoImpl
    ): RegisterRepo

}