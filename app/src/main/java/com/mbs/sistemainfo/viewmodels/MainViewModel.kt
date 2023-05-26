package com.mbs.sistemainfo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbs.sistemainfo.data.CustomerEntity
import com.mbs.sistemainfo.utils.models.CustomerModel
import com.mbs.sistemainfo.repository.CustomerRepository
import com.mbs.sistemainfo.utils.formatCpf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: CustomerRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<List<CustomerModel>>(listOf())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCustomers()
                .distinctUntilChanged()
                .collect {
                    _uiState.value = it.map { customer ->
                        CustomerModel(
                            code = customer.code,
                            name = customer.name,
                            CPF = customer.CPF,
                            phone = if (customer.phone.isNullOrBlank()) "-" else customer.phone,
                            address = if (customer.address.isNullOrBlank()) "-" else customer.address
                        )
                    }
                }
        }
    }

    fun insertCustomer(customer: CustomerModel) {
        val mappedCustomer = CustomerEntity(
            name = customer.name,
            CPF = customer.CPF.formatCpf(),
            phone = customer.phone,
            address = customer.address
        )
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCustomer(mappedCustomer)
        }
    }

    fun deleteCustomer(code: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCustomer(code)
        }
    }
}