package com.example.currency_demo.domain.usecase

import com.example.currency_demo.common.scheduler.FakeSchedulerProvider
import com.example.currency_demo.domain.repository.CurrencyRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version ClearDataTest, v 0.1 Sat 12/14/2024 9:15 PM by Houwen Lie
 */
class ClearDataTest {

    private val repository = mockk<CurrencyRepository>(relaxed = true)

    private val schedulerProvider = FakeSchedulerProvider().immediate()

    private lateinit var clearData: ClearData

    @Before
    fun setup() {
        clearData = ClearData(repository, schedulerProvider)
    }

    @Test
    fun `invoke should call repository clearAllCurrencies`() {
        // given
        every { repository.clearAllCurrencies() } returns Completable.complete()

        // when
        clearData().test().dispose()

        // then
        verify { repository.clearAllCurrencies() }
    }

    @Test
    fun `invoke should be main safe`() {
        // given
        every { repository.clearAllCurrencies() } returns Completable.complete()

        // when
        clearData().test()
            .assertNoErrors()
            .assertComplete()
            .dispose()

        // then
        schedulerProvider.verifyIO()
        schedulerProvider.verifyUI()
    }
}