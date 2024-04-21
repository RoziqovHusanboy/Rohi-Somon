package tj.tajsoft.loyalrsn.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import tj.tajsoft.loyalrsn.data.remote.api.auth.RegisterApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRegisterApi(retrofit: Retrofit) = retrofit.create(RegisterApi::class.java)



}