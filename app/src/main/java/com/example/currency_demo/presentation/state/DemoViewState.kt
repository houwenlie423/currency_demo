package com.example.currency_demo.presentation.state


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version DemoViewState, v 0.1 Fri 12/13/2024 8:26 PM by Houwen Lie
 */
sealed class DemoViewState {

    data object ClearDataSuccess : DemoViewState()

    data object ValidationError : DemoViewState()

    data class GeneralError(val errorMessage: String) : DemoViewState()
}
