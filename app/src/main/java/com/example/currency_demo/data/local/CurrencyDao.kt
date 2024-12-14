package com.example.currency_demo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currency_demo.data.model.CurrencyInfo
import io.reactivex.Completable
import io.reactivex.Flowable


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyInfoDao, v 0.1 Thu 12/12/2024 8:04 PM by Houwen Lie
 */

@Dao
interface CurrencyDao {

    @Query("DELETE FROM currency_info")
    fun clearAllCurrencies(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrency(currencyInfo: CurrencyInfo): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencies(currencies: List<CurrencyInfo>): Completable

    @Query(
        """
         SELECT * FROM currency_info 
         WHERE name LIKE :searchQuery || '%' 
         OR name LIKE '%' || ' ' || :searchQuery || '%' 
         OR symbol LIKE :searchQuery || '%'
         ORDER BY 
             CASE 
                 WHEN code IS NULL OR code = '' THEN 0
                 ELSE 1
             END, 
             name ASC
        """
    )
    fun getCurrencies(searchQuery: String): Flowable<List<CurrencyInfo>>
}