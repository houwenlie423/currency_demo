package com.example.currency_demo.presentation.event

import com.example.currency_demo.data.model.CurrencyInfo


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version DemoEvent, v 0.1 Fri 12/13/2024 8:31 PM by Houwen Lie
 */
sealed class DemoEvent {

    data object ClearButtonClicked : DemoEvent()

    data class AddFiatButtonClicked(val currencies: List<CurrencyInfo>) : DemoEvent()

    data class AddCryptoButtonClicked(val currencies: List<CurrencyInfo>) : DemoEvent()

    data class AddCustomCurrencyButtonClicked(val currency: CurrencyInfo) : DemoEvent()
}
