package com.example.currency_demo.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.currency_demo.common.SampleData
import com.example.currency_demo.common.ValidationException
import com.example.currency_demo.data.model.CurrencyInfo
import com.example.currency_demo.domain.usecase.AddCurrencies
import com.example.currency_demo.domain.usecase.AddCurrency
import com.example.currency_demo.domain.usecase.ClearData
import com.example.currency_demo.presentation.event.DemoEvent
import com.example.currency_demo.presentation.state.DemoViewState
import dagger.Lazy
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import io.reactivex.Completable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version DemoViewModelTest, v 0.1 Sat 12/14/2024 9:28 PM by Houwen Lie
 */
class DemoViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val addCurrency = mockk<AddCurrency>()

    private val addCurrencyLazy = mockk<Lazy<AddCurrency>>()

    private val addCurrencies = mockk<AddCurrencies>()

    private val addCurrenciesLazy = mockk<Lazy<AddCurrencies>>()

    private val clearData = mockk<ClearData>()

    private val clearDataLazy = mockk<Lazy<ClearData>>()

    private val stateObserver = mockk<Observer<DemoViewState>>()

    private lateinit var viewModel: DemoViewModel

    @Before
    fun setup() {
        every { addCurrencyLazy.get() } returns addCurrency
        every { addCurrenciesLazy.get() } returns addCurrencies
        every { clearDataLazy.get() } returns clearData
        viewModel = DemoViewModel(addCurrencyLazy, addCurrenciesLazy, clearDataLazy)

        every { stateObserver.onChanged(any()) } just Runs
    }

    @After
    fun tearDown() {
        unmockkObject(SampleData)
        viewModel.state.removeObserver(stateObserver)
    }

    @Test
    fun `event AddCryptoButtonClicked should call addCurrencies with SampleData SAMPLE_CRYPTO_CURRENCIES`() {
        // given
        stubSampleData()
        every { addCurrencies(any()) } returns Completable.complete()

        // when
        viewModel.dispatchEvent(DemoEvent.AddCryptoButtonClicked)

        // then
        verify { addCurrencies(emptyList()) }
    }

    @Test
    fun `event AddFiatButtonClicked should call addCurrencies with SampleData SAMPLE_FIAT_CURRENCIES`() {
        // given
        stubSampleData()
        every { addCurrencies(any()) } returns Completable.complete()

        // when
        viewModel.dispatchEvent(DemoEvent.AddFiatButtonClicked)

        // then
        verify { addCurrencies(emptyList()) }
    }

    @Test
    fun `event AddCryptoButtonClicked and AddFiatButtonClicked should emit ValidationError if addCurrencies throws it`() {
        // given
        stubSampleData()
        every { addCurrencies(any()) } returns Completable.error(ValidationException())

        // when
        viewModel.state.observeForever(stateObserver)
        viewModel.dispatchEvent(DemoEvent.AddCryptoButtonClicked)
        viewModel.dispatchEvent(DemoEvent.AddFiatButtonClicked)

        // then
        verify (exactly = 2) { stateObserver.onChanged(DemoViewState.ValidationError) }
    }

    @Test
    fun `event AddCryptoButtonClicked and AddFiatButtonClicked should emit GeneralError if addCurrencies throws other exceptions`() {
        // given
        stubSampleData()
        val errorMessage = "errorMessage"
        every { addCurrencies(any()) } returns Completable.error(NullPointerException(errorMessage))

        // when
        viewModel.state.observeForever(stateObserver)
        viewModel.dispatchEvent(DemoEvent.AddCryptoButtonClicked)
        viewModel.dispatchEvent(DemoEvent.AddFiatButtonClicked)

        // then
        verify (exactly = 2) { stateObserver.onChanged(DemoViewState.GeneralError(errorMessage)) }
    }

    @Test
    fun `event ClearButtonClicked should call clearData and emit ClearDataSuccess if clearData is successful`() {
        // given
        every { clearData() } returns Completable.complete()

        // when
        viewModel.state.observeForever(stateObserver)
        viewModel.dispatchEvent(DemoEvent.ClearButtonClicked)

        // then
        verify { clearData() }
        verify { stateObserver.onChanged(DemoViewState.ClearDataSuccess) }
    }

    @Test
    fun `event ClearButtonClicked should call clearData and emit GeneralError if clearData fails`() {
        // given
        val errorMessage = "errorMessage"
        every { clearData() } returns Completable.error(Exception(errorMessage))

        // when
        viewModel.state.observeForever(stateObserver)
        viewModel.dispatchEvent(DemoEvent.ClearButtonClicked)

        // then
        verify { clearData() }
        verify { stateObserver.onChanged(DemoViewState.GeneralError(errorMessage)) }
    }

    @Test
    fun `event AddCustomCurrencyButtonClicked should call addCurrency based on updated fields`() {
        // given
        val id = "id"
        val name = "name"
        val symbol = "symbol"
        val code = "code"
        every { addCurrency(any(), any(), any(), any()) } returns Completable.complete()

        // when
        viewModel.apply {
            dispatchEvent(DemoEvent.CurrencyIdChanged(id))
            dispatchEvent(DemoEvent.CurrencyNameChanged(name))
            dispatchEvent(DemoEvent.CurrencySymbolChanged(symbol))
            dispatchEvent(DemoEvent.CurrencyCodeChanged(code))
            dispatchEvent(DemoEvent.AddCustomCurrencyButtonClicked)
        }

        // then
        verify { addCurrency(id, name, symbol, code) }
    }

    @Test
    fun `event AddCustomCurrencyButtonClicked should emit AddCustomCurrencySuccess if addCurrency is successful`() {
        // given
        val name = "name"
        every { addCurrency(any(), any(), any(), any()) } returns Completable.complete()

        // when
        viewModel.apply {
            state.observeForever(stateObserver)
            dispatchEvent(DemoEvent.CurrencyNameChanged(name))
            dispatchEvent(DemoEvent.AddCustomCurrencyButtonClicked)
        }

        // then
        verify { stateObserver.onChanged(DemoViewState.AddCustomCurrencySuccess(name)) }
    }

    @Test
    fun `event AddCustomCurrencyButtonClicked should emit GeneralError if addCurrency fails`() {
        // given
        val errorMessage = "errorMessage"
        every { addCurrency(any(), any(), any(), any()) } returns Completable.error(Exception(errorMessage))

        // when
        viewModel.apply {
            state.observeForever(stateObserver)
            dispatchEvent(DemoEvent.AddCustomCurrencyButtonClicked)
        }

        // then
        verify { stateObserver.onChanged(DemoViewState.GeneralError(errorMessage)) }
    }

    private fun stubSampleData(
        cryptoResult: List<CurrencyInfo> = emptyList(),
        fiatResult: List<CurrencyInfo> = emptyList(),
    ) {
        mockkObject(SampleData)
        every { SampleData.SAMPLE_CRYPTO_CURRENCIES } returns cryptoResult
        every { SampleData.SAMPLE_FIAT_CURRENCIES } returns fiatResult
    }
}