package com.mbs.sistemainfo.repository

import com.mbs.sistemainfo.data.CustomerDao
import com.mbs.sistemainfo.data.CustomerEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CustomerRepository @Inject constructor(private val dao: CustomerDao) {

    suspend fun insertCustomer(customer: CustomerEntity) = dao.insertCustomer(customer)

    suspend fun deleteCustomer(code: Int) = dao.deleteCustomer(code)

    fun getAllCustomers() = dao.getAllCostumers()
        .flowOn(Dispatchers.IO)
        .conflate()
}