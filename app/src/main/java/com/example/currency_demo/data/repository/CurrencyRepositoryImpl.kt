package com.example.currency_demo.data.repository

import com.example.currency_demo.data.local.CurrencyDao
import com.example.currency_demo.data.model.CurrencyInfo
import com.example.currency_demo.domain.repository.CurrencyRepository
import io.reactivex.Completable
import javax.inject.Inject


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyRepositoryImpl, v 0.1 Thu 12/12/2024 8:21 PM by Houwen Lie
 */

class CurrencyRepositoryImpl @Inject constructor(
    private val dao: CurrencyDao
) : CurrencyRepository {

    override fun clearAllCurrencies(): Completable = dao.clearAllCurrencies()

    override fun addCurrency(currency: CurrencyInfo) = dao.insertCurrency(currency)

    override fun addCurrencies(currencies: List<CurrencyInfo>) = dao.insertCurrencies(currencies)

    override fun getCurrencies(searchQuery: String) = dao.getCurrencies(searchQuery)
}