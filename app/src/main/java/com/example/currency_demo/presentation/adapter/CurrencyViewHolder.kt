package com.example.currency_demo.presentation.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_demo.databinding.ItemCurrencyInfoBinding
import com.example.currency_demo.presentation.model.CurrencyUiModel


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyViewHolder, v 0.1 Fri 12/13/2024 8:14 PM by Houwen Lie
 */
class CurrencyViewHolder(private val binding: ItemCurrencyInfoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(currency: CurrencyUiModel) {
        binding.apply {
            tvCurrencyName.text = currency.name
            tvCurrencySymbol.apply {
                isVisible = currency.showSymbol
                text = currency.symbol
            }
            ivChevron.isVisible = currency.showIcon
        }
    }
}