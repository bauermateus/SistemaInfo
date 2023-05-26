package com.mbs.sistemainfo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mbs.sistemainfo.utils.constants.ApplicationConstants
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: CustomerEntity)

    @Query("DELETE FROM `customer_database.db` WHERE code=:code")
    suspend fun deleteCustomer(code: Int)

    @Query("SELECT * FROM `customer_database.db`")
    fun getAllCostumers(): Flow<List<CustomerEntity>>
}