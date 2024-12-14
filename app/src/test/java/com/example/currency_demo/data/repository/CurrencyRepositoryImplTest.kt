package com.example.currency_demo.data.repository

import com.example.currency_demo.data.local.CurrencyDao
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyRepositoryImplTest, v 0.1 Sat 12/14/2024 8:22 PM by Houwen Lie
 */
class CurrencyRepositoryImplTest {

    private val currencyDao = mockk<CurrencyDao>()

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
}