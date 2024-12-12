package com.example.currency_demo.di

import android.content.Context
import androidx.room.Room
import com.example.currency_demo.data.local.CurrencyDao
import com.example.currency_demo.data.local.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version DatabaseModule, v 0.1 Thu 12/12/2024 8:16 PM by Houwen Lie
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): CurrencyDatabase {
        return Room.databaseBuilder(
            appContext,
            CurrencyDatabase::class.java,
            "currency_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCurrencyInfoDao(database: CurrencyDatabase): CurrencyDao {
        return database.dao
    }
}