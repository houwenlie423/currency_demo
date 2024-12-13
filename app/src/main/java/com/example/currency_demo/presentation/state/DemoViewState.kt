package com.example.currency_demo.presentation.state


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version DemoViewState, v 0.1 Fri 12/13/2024 8:26 PM by Houwen Lie
 */
sealed class DemoViewState {

    data object ClearDataSuccess : DemoViewState()

    data class ClearDataError(val errorMessage: String) :  DemoViewState()

    data object AddFiatCurrenciesSuccess : DemoViewState()

    data class AddFiatCurrenciesError(val errorMessage: String) : DemoViewState()

    data object AddCryptoCurrenciesSuccess : DemoViewState()

    data class AddCryptoCurrenciesError(val errorMessage: String) : DemoViewState()

    data object AddCustomCurrencySuccess : DemoViewState()

    data class AddCustomCurrencyError(val errorMessage: String) : DemoViewState()
}
