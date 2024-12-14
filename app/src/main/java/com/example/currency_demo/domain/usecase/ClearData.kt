package com.example.currency_demo.domain.usecase

import com.example.currency_demo.common.scheduler.SchedulerProvider
import com.example.currency_demo.domain.repository.CurrencyRepository
import io.reactivex.Completable
import javax.inject.Inject


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version ClearData, v 0.1 Sat 12/14/2024 9:55 AM by Houwen Lie
 */
class ClearData @Inject constructor(
    private val repository: CurrencyRepository,
    private val schedulerProvider: SchedulerProvider
) {
    operator fun invoke(): Completable {
        return repository.clearAllCurrencies()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }
}