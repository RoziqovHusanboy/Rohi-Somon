package tj.tajsoft.loyalrsn.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import tj.tajsoft.loyalrsn.data.local.room.dao.HomeDao
import tj.tajsoft.loyalrsn.data.local.room.entity.active_user.ActiveUserEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.fuel.FuelEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.sale.SaleEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.userEntity.UserEntity
import tj.tajsoft.loyalrsn.data.local.room.entity.userTransaction.UserTransactionEntity

@Database(entities = [UserTransactionEntity::class,SaleEntity::class,FuelEntity::class,UserEntity::class,ActiveUserEntity::class], version = 1)
abstract class AppDataBase :RoomDatabase(){
    abstract fun homeDao():HomeDao

}