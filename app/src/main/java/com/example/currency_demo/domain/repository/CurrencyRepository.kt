package com.example.currency_demo.domain.repository

import com.example.currency_demo.data.model.CurrencyInfo
import io.reactivex.Completable
import io.reactivex.Observable


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyRepository, v 0.1 Thu 12/12/2024 8:19 PM by Houwen Lie
 */

interface CurrencyRepository {

    fun clearAllCurrencies(): Completable

    fun addCurrency(currency: CurrencyInfo): Completable

    fun addCurrencies(currencies: List<CurrencyInfo>): Completable

    fun getCurrencies(searchQuery: String): Observable<List<CurrencyInfo>>
}