package com.example.currency_demo.domain.usecase

import com.example.currency_demo.common.ValidationException
import com.example.currency_demo.common.scheduler.FakeSchedulerProvider
import com.example.currency_demo.data.model.CurrencyInfo
import com.example.currency_demo.domain.repository.CurrencyRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version AddCurrenciesTest, v 0.1 Sat 12/14/2024 8:45 PM by Houwen Lie
 */
class AddCurrenciesTest {

    private val repository = mockk<CurrencyRepository>(relaxed = true)

    private val schedulerProvider = FakeSchedulerProvider().immediate()

    private lateinit var addCurrencies: AddCurrencies

    @Before
    fun setup() {
        addCurrencies = AddCurrencies(repository, schedulerProvider)
    }

    @Test
    fun `invoke should call repository addCurrencies if currencies argument is not empty`() {
        // given
        every { repository.addCurrencies(any()) } returns Completable.complete()

        // when
        val currencies = listOf(CurrencyInfo("", "", "", ""))
        addCurrencies(currencies).test().dispose()

        // then
        verify { repository.addCurrencies(currencies) }
    }

    @Test
    fun `invoke should not call repository addCurrencies and instead throw ValidationError if currencies argument is empty`() {
        // given
        every { repository.addCurrencies(any()) } returns Completable.complete()

        // when
        val currencies = emptyList<CurrencyInfo>()
        val testObserver = addCurrencies(currencies).test()

        // then
        verify (exactly = 0) { repository.addCurrencies(currencies) }
        testObserver.assertError { error -> error is ValidationException }
            .dispose()
    }

    @Test
    fun `invoke should be main safe`() {
        // given
        every { repository.addCurrencies(any()) } returns Completable.complete()

        // when
        val currencies = listOf(CurrencyInfo("", "", "", ""))
        addCurrencies(currencies).test()
            .assertComplete()
            .assertNoErrors()
            .dispose()

        // then
        schedulerProvider.verifyIO()
        schedulerProvider.verifyUI()
    }
}