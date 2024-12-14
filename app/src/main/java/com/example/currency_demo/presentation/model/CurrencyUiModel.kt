package com.example.currency_demo.presentation.model


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyUiModel, v 0.1 Sat 12/14/2024 7:10 PM by Houwen Lie
 */
data class CurrencyUiModel(
    val id: String,
    val name: String,
    val symbol: String,
    val showSymbol: Boolean,
    val showIcon: Boolean
)
