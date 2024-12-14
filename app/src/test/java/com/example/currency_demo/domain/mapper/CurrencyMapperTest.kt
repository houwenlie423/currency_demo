package com.example.currency_demo.domain.mapper

import com.example.currency_demo.data.model.CurrencyInfo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyMapperTest, v 0.1 Sat 12/14/2024 9:20 PM by Houwen Lie
 */
class CurrencyMapperTest {

    @Test
    fun `toCurrencyUiModels should return same amount of currencies`() {
        // given
        val totalCount = 5
        val currencyInfos = List(totalCount) { CurrencyInfo(id = "id", name = "name", symbol = "symbol") }

        // when
        val currencyUiModels = currencyInfos.toCurrencyUiModels()
        assertEquals(currencyUiModels.size, totalCount)

    }

    @Test
    fun `toCurrencyUiModel should return id, name and symbol as origin`() {
        // given
        val currencyInfo = CurrencyInfo(id = "id", name = "name", symbol = "symbol")

        // when
        val currencyUiModel = currencyInfo.toCurrencyUiModel()

        // then
        assertEquals(currencyInfo.id, currencyUiModel.id)
        assertEquals(currencyInfo.name, currencyUiModel.name)
        assertEquals(currencyInfo.symbol, currencyUiModel.symbol)
    }

    @Test
    fun `toCurrencyUiModel should have showSymbol and showIcon false if code is not blank`() {
        // given
        val currencyInfo = CurrencyInfo(id = "id", name = "name", symbol = "symbol", code = "code")

        // when
        val currencyUiModel = currencyInfo.toCurrencyUiModel()

        // then
        assertFalse(currencyUiModel.showSymbol)
        assertFalse(currencyUiModel.showIcon)
    }

    @Test
    fun `toCurrencyUiModel should have showSymbol and showIcon true if code is empty`() {
        // given
        val currencyInfo = CurrencyInfo(id = "id", name = "name", symbol = "symbol", code = "")

        // when
        val currencyUiModel = currencyInfo.toCurrencyUiModel()

        // then
        assertTrue(currencyUiModel.showSymbol)
        assertTrue(currencyUiModel.showIcon)
    }

    @Test
    fun `toCurrencyUiModel should have showSymbol and showIcon true if code only contains white space`() {
        // given
        val currencyInfo = CurrencyInfo(id = "id", name = "name", symbol = "symbol", code = "   ")

        // when
        val currencyUiModel = currencyInfo.toCurrencyUiModel()

        // then
        assertTrue(currencyUiModel.showSymbol)
        assertTrue(currencyUiModel.showIcon)
    }
}