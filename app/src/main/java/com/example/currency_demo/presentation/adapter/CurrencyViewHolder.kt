package com.example.currency_demo.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.currency_demo.data.model.CurrencyInfo
import com.example.currency_demo.databinding.ItemCurrencyInfoBinding


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyViewHolder, v 0.1 Fri 12/13/2024 8:14 PM by Houwen Lie
 */
class CurrencyViewHolder(private val binding: ItemCurrencyInfoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(currencyInfo: CurrencyInfo) {
        binding.apply {
            tvCurrencyName.text = currencyInfo.name
            tvCurrencySymbol.text = currencyInfo.symbol
        }
    }
}