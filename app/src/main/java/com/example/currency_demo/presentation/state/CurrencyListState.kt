package com.example.currency_demo.presentation.state

import com.example.currency_demo.presentation.model.CurrencyUiModel


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyListState, v 0.1 Fri 12/13/2024 8:33 PM by Houwen Lie
 */
sealed class CurrencyListState {

    data class CurrenciesUpdated (val currencies: List<CurrencyUiModel>) : CurrencyListState()

    data object CurrenciesNotFound : CurrencyListState()

    data class Error (val errorMessage: String) : CurrencyListState()
}
