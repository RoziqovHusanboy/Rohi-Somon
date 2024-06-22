package tj.tajsoft.loyalrsn.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tj.tajsoft.loyalrsn.data.local.room.entity.active_user.ActiveUserEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.fuel.FuelEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.sale.SaleEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.userEntity.UserEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.userTransaction.UserTransactionEntity

@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(userTransactionEntity: List<UserTransactionEntity>):Unit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSale(saleEntity: List<SaleEntity>):Unit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFuel(fuelEntity: List<FuelEntity>):Unit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity:List<UserEntity>):Unit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserActive(activeUserEntity: ActiveUserEntity):Long

    @Query("SELECT * FROM activeUserEntity")
    fun getUserActive(): Flow<ActiveUserEntity>

    @Query("select * from userTransactionEntity")
    fun getTransaction(): Flow<List<UserTransactionEntity>>

    @Query("select * from saleEntity")
    fun getSale(): Flow<List<SaleEntity>>

    @Query("select * from fuelEntity")
    fun getFuel(): Flow<List<FuelEntity>>

    @Query("select * from userEntity")
    fun getUser(): Flow<List<UserEntity>>

    @Query("DELETE FROM activeUserEntity")
    suspend fun clearUserActive()

    @Query("DELETE FROM userEntity")
    suspend fun clearUser()

    @Query("DELETE FROM fuelEntity")
    suspend fun clearFuel()

    @Query("DELETE FROM saleEntity")
    suspend fun clearSale()

    @Query("DELETE FROM userTransactionEntity")
    suspend fun clearTransaction()


}