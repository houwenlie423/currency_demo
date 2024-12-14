package com.example.currency_demo.domain.usecase

import com.example.currency_demo.common.scheduler.FakeSchedulerProvider
import com.example.currency_demo.data.model.CurrencyInfo
import com.example.currency_demo.domain.repository.CurrencyRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import kotlin.random.Random


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version GetCurrenciesTest, v 0.1 Sat 12/14/2024 8:37 PM by Houwen Lie
 */
class GetCurrenciesTest {

    private val repository = mockk<CurrencyRepository>()

    private val schedulerProvider = FakeSchedulerProvider().immediate()

    private lateinit var getCurrencies: GetCurrencies

    @Before
    fun setup() {
        getCurrencies = GetCurrencies(repository, schedulerProvider)
    }

    @Test
    fun `invoke should call repository getCurrencies`() {
        // given
        every { repository.getCurrencies(any()) } returns Flowable.just(emptyList())

        // when
        val query = Random.nextInt().toString()
        getCurrencies(query).test().dispose()

        // then
        verify { repository.getCurrencies(query) }
    }

    @Test
    fun `invoke should not emit downstream if upstream publishes same items`() {
        // given
        val currencies = listOf(CurrencyInfo("", "", "", ""))
        every {
            repository.getCurrencies(any())
        } returns Flowable.just(currencies, currencies)

        // when
        val query = Random.nextInt().toString()
        val testObserver = getCurrencies(query).test()

        // then
        testObserver.assertValueCount(1)
            .assertValue(currencies)
            .assertComplete()
            .assertNoErrors()
            .dispose()
    }

    @Test
    fun `invoke should be main safe`() {
        // given
        every { repository.getCurrencies(any()) } returns Flowable.just(emptyList())

        // when
        val query = Random.nextInt().toString()
        getCurrencies(query).test().dispose()

        // then
        schedulerProvider.verifyIO()
        schedulerProvider.verifyUI()
    }
}