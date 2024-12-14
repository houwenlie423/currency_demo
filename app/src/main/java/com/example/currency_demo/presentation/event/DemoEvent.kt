package com.example.currency_demo.presentation.event

import com.example.currency_demo.data.model.CurrencyInfo


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version DemoEvent, v 0.1 Fri 12/13/2024 8:31 PM by Houwen Lie
 */
sealed class DemoEvent {

    data object ClearButtonClicked : DemoEvent()

    data object AddFiatButtonClicked : DemoEvent()

    data object AddCryptoButtonClicked : DemoEvent()

    data object AddCustomCurrencyButtonClicked: DemoEvent()

    data class CurrencyIdChanged(val id: String) : DemoEvent()

    data class CurrencyNameChanged(val name: String) : DemoEvent()

    data class CurrencySymbolChanged(val symbol: String) : DemoEvent()

    data class CurrencyCodeChanged(val code: String) : DemoEvent()

}
