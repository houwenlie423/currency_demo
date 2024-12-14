package com.example.currency_demo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.currency_demo.common.RxLifecycleVM
import com.example.currency_demo.common.SampleData
import com.example.currency_demo.common.ValidationException
import com.example.currency_demo.common.collectBy
import com.example.currency_demo.data.model.CurrencyInfo
import com.example.currency_demo.domain.usecase.AddCurrencies
import com.example.currency_demo.domain.usecase.AddCurrency
import com.example.currency_demo.domain.usecase.ClearData
import com.example.currency_demo.presentation.event.DemoEvent
import com.example.currency_demo.presentation.state.DemoViewState
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version DemoViewModel, v 0.1 Fri 12/13/2024 8:37 PM by Houwen Lie
 */
@HiltViewModel
class DemoViewModel @Inject constructor(
    private val addCurrency: Lazy<AddCurrency>,
    private val addCurrencies: Lazy<AddCurrencies>,
    private val clearData: Lazy<ClearData>,
) : RxLifecycleVM<DemoViewState, DemoEvent>() {

    private val _state = MutableLiveData<DemoViewState>()

    override val state: LiveData<DemoViewState> get() = _state

    private val currencyId = BehaviorSubject.createDefault("")

    private val currencyName = BehaviorSubject.createDefault("")

    private val currencySymbol = BehaviorSubject.createDefault("")

    private val currencyCode = BehaviorSubject.createDefault("")

    override fun dispatchEvent(event: DemoEvent) {
        when (event) {
            is DemoEvent.AddCryptoButtonClicked -> addCurrencies(currencies = SampleData.SAMPLE_CRYPTO_CURRENCIES)

            is DemoEvent.AddFiatButtonClicked -> addCurrencies(currencies = SampleData.SAMPLE_FIAT_CURRENCIES)

            is DemoEvent.AddCustomCurrencyButtonClicked -> addCustomCurrency()

            is DemoEvent.ClearButtonClicked -> clearData()

            is DemoEvent.CurrencyIdChanged -> updateCurrencyId(event.id)

            is DemoEvent.CurrencyNameChanged -> updateCurrencyName(event.name)

            is DemoEvent.CurrencySymbolChanged -> updateCurrencySymbol(event.symbol)

            is DemoEvent.CurrencyCodeChanged -> updateCurrencyCode(event.code)
        }
    }

    private fun addCurrencies(currencies: List<CurrencyInfo>) {
        addCurrencies.get().invoke(currencies)
            .doOnError { error ->
                if (error is ValidationException) {
                    _state.value = DemoViewState.ValidationError
                } else {
                    _state.value = DemoViewState.GeneralError(error.message.orEmpty())
                }
            }
            .onErrorComplete()
            .collectBy(disposeBag)
    }

    private fun addCustomCurrency() {
        val id = currencyId.value.orEmpty()
        val name = currencyName.value.orEmpty()
        val symbol = currencySymbol.value.orEmpty()
        val code = currencyCode.value.orEmpty()

        addCurrency.get().invoke(id, name, symbol, code)
            .doOnComplete { _state.value = DemoViewState.AddCustomCurrencySuccess(name) }
            .doOnError { error ->
                if (error is ValidationException) {
                    _state.value = DemoViewState.ValidationError
                } else {
                    _state.value = DemoViewState.GeneralError(error.message.orEmpty())
                }
            }
            .onErrorComplete()
            .collectBy(disposeBag)
    }

    private fun clearData() {
        clearData.get().invoke()
            .doOnComplete { _state.value = DemoViewState.ClearDataSuccess }
            .doOnError { error -> _state.value = DemoViewState.GeneralError(error.message.orEmpty()) }
            .onErrorComplete()
            .collectBy(disposeBag)
    }

    private fun updateCurrencyId(id: String) {
        currencyId.onNext(id)
    }

    private fun updateCurrencyName(name: String) {
        currencyName.onNext(name)
    }

    private fun updateCurrencySymbol(symbol: String) {
        currencySymbol.onNext(symbol)
    }

    private fun updateCurrencyCode(code: String) {
        currencyCode.onNext(code)
    }

}