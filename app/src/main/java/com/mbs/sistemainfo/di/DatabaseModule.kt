package com.mbs.sistemainfo.di

import android.content.Context
import androidx.room.Room
import com.mbs.sistemainfo.data.CustomerDao
import com.mbs.sistemainfo.data.CustomerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideNoteDao(database: CustomerDatabase): CustomerDao {
        return database.customerDao()
    }

    @Singleton
    @Provides
    fun provideNoteDatabase(@ApplicationContext context: Context): CustomerDatabase {
        return CustomerDatabase.getInstance(context)
    }
}