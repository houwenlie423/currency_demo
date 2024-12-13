package com.example.currency_demo.presentation.event


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyListEvent, v 0.1 Fri 12/13/2024 8:34 PM by Houwen Lie
 */
sealed class CurrencyListEvent {

    data class SearchQueryUpdated(val searchQuery: String) : CurrencyListEvent()
}
