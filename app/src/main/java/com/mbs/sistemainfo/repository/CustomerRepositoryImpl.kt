package com.mbs.sistemainfo.repository

import com.mbs.sistemainfo.data.CustomerDao
import com.mbs.sistemainfo.data.CustomerEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(private val dao: CustomerDao) : CustomerRepositoryInterface {

    override suspend fun insertCustomer(customer: CustomerEntity) = dao.insertCustomer(customer)

    override suspend fun deleteCustomer(code: Int) = dao.deleteCustomer(code)

    override fun getAllCustomers() = dao.getAllCostumers()
        .flowOn(Dispatchers.IO)
        .conflate()
}