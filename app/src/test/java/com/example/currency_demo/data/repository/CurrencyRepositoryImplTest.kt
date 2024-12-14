package com.example.currency_demo.data.repository

import com.example.currency_demo.data.local.CurrencyDao
import com.example.currency_demo.data.model.CurrencyInfo
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyRepositoryImplTest, v 0.1 Sat 12/14/2024 8:22 PM by Houwen Lie
 */
class CurrencyRepositoryImplTest {

    private val currencyDao = mockk<CurrencyDao>(relaxed = true)

    private lateinit var repository: CurrencyRepositoryImpl

    @Before
    fun setup() {
        repository = CurrencyRepositoryImpl(currencyDao)
    }

    @Test
    fun `clearAllCurrencies should call currencyDao clearAllCurrencies`() {
        // given
        every { currencyDao.clearAllCurrencies() } returns Completable.complete()

        // when
        repository.clearAllCurrencies().test().dispose()

        // then
        verify { currencyDao.clearAllCurrencies() }
    }

    @Test
    fun `addCurrency should call currencyDao insertCurrency`() {
        // given
        every { currencyDao.insertCurrency(any()) } returns Completable.complete()

        // when
        val currencyInfo = CurrencyInfo("", "", "", "")
        repository.addCurrency(currencyInfo).test().dispose()

        // then
        verify { currencyDao.insertCurrency(currencyInfo) }
    }

    @Test
    fun `addCurrencies should call currencyDao insertCurrencies`() {
        // given
        every { currencyDao.insertCurrencies(any()) } returns Completable.complete()

        // when
        val currencies = listOf(CurrencyInfo("", "", "", ""))
        repository.addCurrencies(currencies).test().dispose()

        // then
        verify { currencyDao.insertCurrencies(currencies) }
    }

    @Test
    fun `getCurrencies should call currencyDao getCurrencies`() {
        // given
        every { currencyDao.getCurrencies(any()) } returns Flowable.just(emptyList())

        // when
        val query = Random.nextInt().toString()
        repository.getCurrencies(query).test().dispose()

        // then
        verify { currencyDao.getCurrencies(query) }
    }
}