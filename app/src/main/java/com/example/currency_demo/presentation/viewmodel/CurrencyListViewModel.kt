package com.example.currency_demo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.currency_demo.common.RxLifecycleVM
import com.example.currency_demo.common.scheduler.SchedulerProvider
import com.example.currency_demo.domain.repository.CurrencyRepository
import com.example.currency_demo.presentation.event.CurrencyListEvent
import com.example.currency_demo.presentation.state.CurrencyListState
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyListViewModel, v 0.1 Fri 12/13/2024 8:48 PM by Houwen Lie
 */
@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    // TODO -> replace with use cases
    private val repository: Lazy<CurrencyRepository>,
    private val schedulerProvider: SchedulerProvider
) : RxLifecycleVM<CurrencyListState, CurrencyListEvent>() {

    private val _state = MutableLiveData<CurrencyListState>()

    override val state: LiveData<CurrencyListState> get() = _state

    init {
        repository.get()
            .getCurrencies("")
            .map<CurrencyListState> { currencies -> CurrencyListState.CurrenciesUpdated(currencies) }
            .onErrorReturn { error -> CurrencyListState.Error(error.message.orEmpty()) }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnNext { viewState -> _state.value = viewState }
            .subscribe()
            .addTo(disposeBag)
    }

    override fun dispatchEvent(event: CurrencyListEvent) {
        TODO("Not yet implemented")
    }
}