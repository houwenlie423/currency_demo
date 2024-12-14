package com.example.currency_demo.domain.usecase

import com.example.currency_demo.common.scheduler.SchedulerProvider
import com.example.currency_demo.data.model.CurrencyInfo
import com.example.currency_demo.domain.repository.CurrencyRepository
import io.reactivex.Observable
import javax.inject.Inject


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version GetCurrencies, v 0.1 Sat 12/14/2024 10:02 AM by Houwen Lie
 */
class GetCurrencies @Inject constructor(
    private val repository: CurrencyRepository,
    private val schedulerProvider: SchedulerProvider
) {
    operator fun invoke(searchQuery: String): Observable<List<CurrencyInfo>> {
        return repository.getCurrencies(searchQuery)
            .distinctUntilChanged()
            .onBackpressureLatest()
            .toObservable()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }
}