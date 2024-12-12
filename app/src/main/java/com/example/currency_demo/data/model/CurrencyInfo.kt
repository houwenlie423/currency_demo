package com.example.currency_demo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyInfo, v 0.1 Thu 12/12/2024 7:52 PM by Houwen Lie
 */
@Entity(tableName = "currency_info")
data class CurrencyInfo(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String,
    val code: String = ""
)