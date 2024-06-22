package tj.tajsoft.loyalrsn.di

import android.content.Context
import androidx.room.Room
 import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import tj.tajsoft.loyalrsn.data.local.room.database.AppDataBase
import tj.tajsoft.loyalrsn.data.local.room.dao.HomeDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "app_database"
       ).build()
    }

    @Provides
    fun provideHomeDao(db: AppDataBase): HomeDao {
        return db.homeDao()
    }
}
