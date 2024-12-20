package com.example.currency_demo.common

import com.example.currency_demo.data.model.CurrencyInfo


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version SampleData, v 0.1 Sat 12/14/2024 10:36 AM by Houwen Lie
 */
object SampleData {

    val SAMPLE_CRYPTO_CURRENCIES = listOf(
        CurrencyInfo(id = "BTC", name = "Bitcoin", symbol = "BTC"),
        CurrencyInfo(id = "ETH", name = "Ethereum", symbol = "ETH"),
        CurrencyInfo(id = "XRP", name = "XRP", symbol = "XRP"),
        CurrencyInfo(id = "BCH", name = "Bitcoin Cash", symbol = "BCH"),
        CurrencyInfo(id = "LTC", name = "Litecoin", symbol = "LTC"),
        CurrencyInfo(id = "EOS", name = "EOS", symbol = "EOS"),
        CurrencyInfo(id = "BNB", name = "Binance Coin", symbol = "BNB"),
        CurrencyInfo(id = "LINK", name = "Chainlink", symbol = "LINK"),
        CurrencyInfo(id = "NEO", name = "NEO", symbol = "NEO"),
        CurrencyInfo(id = "ETC", name = "Ethereum Classic", symbol = "ETC"),
        CurrencyInfo(id = "ONT", name = "Ontology", symbol = "ONT"),
        CurrencyInfo(id = "CRO", name = "Crypto.com Chain", symbol = "CRO"),
        CurrencyInfo(id = "CUC", name = "Cucumber", symbol = "CUC"),
        CurrencyInfo(id = "USDC", name = "USD Coin", symbol = "USDC")
    )

    val SAMPLE_FIAT_CURRENCIES = listOf(
        CurrencyInfo(id = "SGD", name = "Singapore Dollar", symbol = "$", code = "SGD"),
        CurrencyInfo(id = "EUR", name = "Euro", symbol = "€", code = "EUR"),
        CurrencyInfo(id = "GBP", name = "British Pound", symbol = "£", code = "GBP"),
        CurrencyInfo(id = "HKD", name = "Hong Kong Dollar", symbol = "$", code = "HKD"),
        CurrencyInfo(id = "JPY", name = "Japanese Yen", symbol = "¥", code = "JPY"),
        CurrencyInfo(id = "AUD", name = "Australian Dollar", symbol = "$", code = "AUD"),
        CurrencyInfo(id = "USD", name = "United States Dollar", symbol = "$", code = "USD")
    )

}