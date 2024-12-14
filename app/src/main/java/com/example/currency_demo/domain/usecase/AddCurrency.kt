package com.example.currency_demo.domain.usecase

import com.example.currency_demo.common.ValidationException
import com.example.currency_demo.common.scheduler.SchedulerProvider
import com.example.currency_demo.data.model.CurrencyInfo
import com.example.currency_demo.domain.repository.CurrencyRepository
import io.reactivex.Completable
import javax.inject.Inject


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version AddCurrency, v 0.1 Sat 12/14/2024 9:56 AM by Houwen Lie
 */
class AddCurrency @Inject constructor(
    private val repository: CurrencyRepository,
    private val schedulerProvider: SchedulerProvider
) {
    operator fun invoke(id: String, name: String, symbol: String, code: String): Completable {
        if (id.isBlank() || name.isBlank() || symbol.isBlank()) {
            return Completable.error(ValidationException())
        }

        val currency = CurrencyInfo(id, name, symbol, code)
        return repository.addCurrency(currency)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }
}