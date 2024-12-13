package com.example.currency_demo.di

import com.example.currency_demo.common.scheduler.SchedulerProvider
import com.example.currency_demo.common.scheduler.SchedulerProviderImpl
import com.example.currency_demo.data.local.CurrencyDao
import com.example.currency_demo.data.local.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version AppModule, v 0.1 Fri 12/13/2024 8:20 PM by Houwen Lie
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl(asyncMessaging = true)
}