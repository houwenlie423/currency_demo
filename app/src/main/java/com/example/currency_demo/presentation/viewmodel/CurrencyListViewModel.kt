package com.example.currency_demo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.currency_demo.common.RxLifecycleVM
import com.example.currency_demo.common.collectBy
import com.example.currency_demo.common.scheduler.SchedulerProvider
import com.example.currency_demo.domain.usecase.GetCurrencies
import com.example.currency_demo.presentation.event.CurrencyListEvent
import com.example.currency_demo.presentation.state.CurrencyListState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyListViewModel, v 0.1 Fri 12/13/2024 8:48 PM by Houwen Lie
 */
@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val getCurrencies: GetCurrencies,
    private val schedulerProvider: SchedulerProvider
) : RxLifecycleVM<CurrencyListState, CurrencyListEvent>() {

    private val _state = MutableLiveData<CurrencyListState>()

    override val state: LiveData<CurrencyListState> get() = _state

    private val searchQuery = BehaviorSubject.createDefault("")

    init {
        observeCurrencies()
    }

    override fun dispatchEvent(event: CurrencyListEvent) {
        when (event) {
            is CurrencyListEvent.SearchQueryUpdated -> updateSearchQuery(event.searchQuery)
        }
    }

    private fun updateSearchQuery(newSearchQuery: String) {
        searchQuery.onNext(newSearchQuery)
    }

    private fun observeCurrencies() {
        observeSearchQuery()
            .switchMap { query ->
                getCurrencies(query)
                    .map { currencies ->
                        if (currencies.isEmpty()) {
                            CurrencyListState.CurrenciesNotFound
                        } else {
                            CurrencyListState.CurrenciesUpdated(currencies)
                        }
                    }
                    .onErrorReturn { error -> CurrencyListState.Error(error.message.orEmpty()) }
                    .doOnNext { newState -> _state.value = newState }
            }
            .collectBy(disposeBag)
    }

    private fun observeSearchQuery(): Observable<String> {
        return searchQuery.hide()
            .debounce(QUERY_DEBOUNCE_MILLIS, TimeUnit.MILLISECONDS, schedulerProvider.computation())
            .startWith("")
    }

    companion object {

        private const val QUERY_DEBOUNCE_MILLIS = 300L
    }
}