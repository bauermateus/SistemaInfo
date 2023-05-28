package com.mbs.sistemainfo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mbs.sistemainfo.utils.constants.ApplicationConstants

@Database(entities = [CustomerEntity::class], version = 1, exportSchema = false)
abstract class CustomerDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao

    // Database Singleton
    companion object {

        private var instance: CustomerDatabase? = null

        fun getInstance(context: Context): CustomerDatabase {
            if (instance == null) {
                synchronized(CustomerDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CustomerDatabase::class.java, ApplicationConstants.DATABASE_NAME
                    )
                        .build()
                }
            }
            return instance!!
        }
    }
}