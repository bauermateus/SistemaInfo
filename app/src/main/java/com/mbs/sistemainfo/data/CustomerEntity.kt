package com.mbs.sistemainfo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mbs.sistemainfo.utils.constants.ApplicationConstants

@Entity(tableName = ApplicationConstants.DATABASE_NAME)
data class CustomerEntity (

    @ColumnInfo(name = "code")
    @PrimaryKey(autoGenerate = true)
    val code: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "CPF")
    val CPF: String,

    @ColumnInfo(name = "phone")
    val phone: String?,

    @ColumnInfo(name = "address")
    val address: String?
    )