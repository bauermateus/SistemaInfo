package com.mbs.sistemainfo.utils.models

data class CustomerModel(
    val code: Int?,
    val name: String,
    val CPF: String,
    val phone: String?,
    val address: String?
)