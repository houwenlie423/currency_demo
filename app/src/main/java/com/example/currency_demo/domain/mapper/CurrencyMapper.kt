package com.example.currency_demo.domain.mapper

import com.example.currency_demo.data.model.CurrencyInfo
import com.example.currency_demo.presentation.model.CurrencyUiModel


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyMapper, v 0.1 Sat 12/14/2024 7:15 PM by Houwen Lie
 */

fun List<CurrencyInfo>.toCurrencyUiModels() = map { currency -> currency.toCurrencyUiModel() }

fun CurrencyInfo.toCurrencyUiModel(): CurrencyUiModel {
    val isCrypto = code.isBlank()
    return CurrencyUiModel(
        id = id,
        name = name,
        symbol = symbol,
        showSymbol = isCrypto,
        showIcon = isCrypto
    )
}