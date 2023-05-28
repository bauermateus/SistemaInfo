package com.mbs.sistemainfo.repository

import com.mbs.sistemainfo.data.CustomerEntity
import kotlinx.coroutines.flow.Flow

interface CustomerRepositoryInterface {
    suspend fun insertCustomer(customer: CustomerEntity)
    suspend fun deleteCustomer(code: Int)
    fun getAllCustomers(): Flow<List<CustomerEntity>>
}