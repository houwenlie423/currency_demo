package com.example.currency_demo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.currency_demo.common.RxLifecycleVM
import com.example.currency_demo.common.scheduler.SchedulerProvider
import com.example.currency_demo.data.model.CurrencyInfo
import com.example.currency_demo.domain.repository.CurrencyRepository
import com.example.currency_demo.domain.usecase.AddCurrencies
import com.example.currency_demo.domain.usecase.AddCurrency
import com.example.currency_demo.domain.usecase.ClearData
import com.example.currency_demo.presentation.event.DemoEvent
import com.example.currency_demo.presentation.state.DemoViewState
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
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
            is DemoEvent.AddCryptoButtonClicked -> TODO()

            is DemoEvent.AddCustomCurrencyButtonClicked -> TODO()

            is DemoEvent.AddFiatButtonClicked -> TODO()

            is DemoEvent.ClearButtonClicked -> TODO()

            is DemoEvent.CurrencyIdChanged -> updateCurrencyId(event.id)

            is DemoEvent.CurrencyNameChanged -> updateCurrencyName(event.name)

            is DemoEvent.CurrencySymbolChanged -> updateCurrencySymbol(event.symbol)

            is DemoEvent.CurrencyCodeChanged -> updateCurrencyCode(event.code)
        }
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