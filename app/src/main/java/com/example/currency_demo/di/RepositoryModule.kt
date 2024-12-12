package com.example.currency_demo.di

import com.example.currency_demo.data.repository.CurrencyRepositoryImpl
import com.example.currency_demo.domain.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version RepositoryModule, v 0.1 Thu 12/12/2024 8:24 PM by Houwen Lie
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCurrencyRepository(
        currencyRepositoryImpl: CurrencyRepositoryImpl
    ): CurrencyRepository
}