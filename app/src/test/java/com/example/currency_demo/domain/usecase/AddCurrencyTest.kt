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
import kotlin.random.Random


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version AddCurrencyTest, v 0.1 Sat 12/14/2024 8:51 PM by Houwen Lie
 */
class AddCurrencyTest {

    private val repository = mockk<CurrencyRepository>()

    private val schedulerProvider = FakeSchedulerProvider().immediate()

    private lateinit var addCurrency: AddCurrency

    @Before
    fun setup() {
        addCurrency = AddCurrency(repository, schedulerProvider)
    }

    @Test
    fun `invoke should call repository addCurrency if arguments are valid`() {
        // given
        every { repository.addCurrency(any()) } returns Completable.complete()

        // when
        val currencyInfo = CurrencyInfo(id = "id", name = "name", symbol = "symbol", code = "code")
        addCurrency(currencyInfo.id, currencyInfo.name, currencyInfo.symbol, currencyInfo.code)
            .test()
            .dispose()

        // then
        verify { repository.addCurrency(currencyInfo) }
    }

    @Test
    fun `invoke should not call repository addCurrency and instead throw ValidationException if any argument is blank`() {
        // given
        every { repository.addCurrency(any()) } returns Completable.complete()

        // when
        val arguments = listOf(
            Triple("", "name", "symbol"),
            Triple(" ", "name", "symbol"),
            Triple("id", "", "symbol"),
            Triple("id", " ", "symbol"),
            Triple("id", "name", ""),
            Triple("id", "name", " "),
            Triple("", "", ""),
            Triple(" ", " ", " "),
        )
        for ((id, name, symbol) in arguments) {
            val code = Random.nextInt().toString()
            addCurrency(id, name, symbol, code)
                .test()
                .assertError { error -> error is ValidationException }
                .dispose()
        }

        // then
        verify(exactly = 0) { repository.addCurrency(any()) }
    }

    @Test
    fun `invoke should be main safe`() {
        // given
        every { repository.addCurrency(any()) } returns Completable.complete()

        // when
        val currencyInfo = CurrencyInfo(id = "id", name = "name", symbol = "symbol", code = "code")
        addCurrency(currencyInfo.id, currencyInfo.name, currencyInfo.symbol, currencyInfo.code)
            .test()
            .assertComplete()
            .assertNoErrors()
            .dispose()

        // then
        schedulerProvider.verifyIO()
        schedulerProvider.verifyUI()
    }
}