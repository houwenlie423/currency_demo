package com.example.currency_demo.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.currency_demo.common.scheduler.FakeSchedulerProvider
import com.example.currency_demo.data.model.CurrencyInfo
import com.example.currency_demo.domain.mapper.toCurrencyUiModels
import com.example.currency_demo.domain.usecase.GetCurrencies
import com.example.currency_demo.presentation.event.CurrencyListEvent
import com.example.currency_demo.presentation.state.CurrencyListState
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyListViewModelTest, v 0.1 Sat 12/14/2024 10:13 PM by Houwen Lie
 */
class CurrencyListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val getCurrencies = mockk<GetCurrencies>(relaxed = true)

    private val schedulerProvider = FakeSchedulerProvider().immediate()

    private val stateObserver = mockk<Observer<CurrencyListState>>()

    private lateinit var viewModel: CurrencyListViewModel

    @Before
    fun setup() {
        every { stateObserver.onChanged(any()) } just Runs
    }

    @After
    fun tearDown() {
        schedulerProvider.reset()
        viewModel.state.removeObserver(stateObserver)
    }

    @Test
    fun `viewModel should call getCurrencies with empty query on initialization`() {
        // given
        every { getCurrencies(any()) } returns Observable.just(emptyList())
        viewModel = CurrencyListViewModel(getCurrencies, schedulerProvider)

        // then
        verify { getCurrencies("") }
    }

    @Test
    fun `should only called getCurrencies with query after QUERY_DEBOUNCE_MILLIS`() {
        // given
        schedulerProvider.test()
        viewModel = CurrencyListViewModel(getCurrencies, schedulerProvider)
        val firstQuery = "firstQuery"
        val secondQuery = "secondQuery"
        every { getCurrencies(any()) } returns Observable.just(emptyList())

        // when
        viewModel.apply {
            dispatchEvent(CurrencyListEvent.SearchQueryUpdated(firstQuery))
            dispatchEvent(CurrencyListEvent.SearchQueryUpdated(secondQuery))
            schedulerProvider.advanceTimeBy(
                CurrencyListViewModel.QUERY_DEBOUNCE_MILLIS,
                TimeUnit.MILLISECONDS
            )
        }

        // then
        verify(exactly = 0) { getCurrencies(firstQuery) }
        verify { getCurrencies(secondQuery) }
        schedulerProvider.verifyComputation()
    }

    @Test
    fun `viewModel should emit CurrenciesNotFound if getCurrencies returns empty`() {
        // given
        every { getCurrencies(any()) } returns Observable.just(emptyList())
        viewModel = CurrencyListViewModel(getCurrencies, schedulerProvider)
        viewModel.state.observeForever(stateObserver)

        // then
        verify { stateObserver.onChanged(CurrencyListState.CurrenciesNotFound) }
    }

    @Test
    fun `viewModel should emit CurrenciesUpdated if getCurrencies returns valid list`() {
        // given
        val currencies = listOf(CurrencyInfo("", "", "", ""))
        every { getCurrencies(any()) } returns Observable.just(currencies)
        viewModel = CurrencyListViewModel(getCurrencies, schedulerProvider)
        viewModel.state.observeForever(stateObserver)

        // then
        val currencyUiModels = currencies.toCurrencyUiModels()
        verify { stateObserver.onChanged(CurrencyListState.CurrenciesUpdated(currencyUiModels)) }
    }

    @Test
    fun `viewModel should emit Error if getCurrencies throws Exception`() {
        // given
        val errorMessage = "errorMessage"
        every { getCurrencies(any()) } returns Observable.error(Exception(errorMessage))
        viewModel = CurrencyListViewModel(getCurrencies, schedulerProvider)
        viewModel.state.observeForever(stateObserver)

        // then
        verify { stateObserver.onChanged(CurrencyListState.Error(errorMessage)) }
    }
}