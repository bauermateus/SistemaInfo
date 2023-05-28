package com.mbs.sistemainfo.di

import com.mbs.sistemainfo.repository.CustomerRepositoryImpl
import com.mbs.sistemainfo.repository.CustomerRepositoryInterface
import com.mbs.sistemainfo.repository.LoginRepositoryImpl
import com.mbs.sistemainfo.repository.LoginRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideLoginRepository(impl: LoginRepositoryImpl): LoginRepositoryInterface

    @Binds
    @Singleton
    abstract fun provideCustomerRepository(impl: CustomerRepositoryImpl): CustomerRepositoryInterface
}