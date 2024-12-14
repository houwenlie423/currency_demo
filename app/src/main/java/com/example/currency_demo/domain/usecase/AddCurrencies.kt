package com.example.currency_demo.domain.usecase

import com.example.currency_demo.common.ValidationException
import com.example.currency_demo.common.scheduler.SchedulerProvider
import com.example.currency_demo.data.model.CurrencyInfo
import com.example.currency_demo.domain.repository.CurrencyRepository
import io.reactivex.Completable
import javax.inject.Inject


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version AddCurrencies, v 0.1 Sat 12/14/2024 9:59 AM by Houwen Lie
 */
class AddCurrencies @Inject constructor(
    private val repository: CurrencyRepository,
    private val schedulerProvider: SchedulerProvider
) {
    operator fun invoke(currencies: List<CurrencyInfo>): Completable {
        if (currencies.isEmpty()) return Completable.error(ValidationException())

        return repository.addCurrencies(currencies)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }
}