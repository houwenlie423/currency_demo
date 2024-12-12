package com.example.currency_demo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.currency_demo.data.model.CurrencyInfo


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyDatabase, v 0.1 Thu 12/12/2024 8:10 PM by Houwen Lie
 */

@Database(
    entities = [CurrencyInfo::class],
    version = 1
)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract val dao: CurrencyDao
}