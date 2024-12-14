package com.example.currency_demo.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.currency_demo.presentation.model.CurrencyUiModel


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyDiffCallback, v 0.1 Fri 12/13/2024 8:13 PM by Houwen Lie
 */
object CurrencyDiffCallback : DiffUtil.ItemCallback<CurrencyUiModel>() {

    override fun areItemsTheSame(oldItem: CurrencyUiModel, newItem: CurrencyUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CurrencyUiModel, newItem: CurrencyUiModel): Boolean {
        return oldItem == newItem
    }
}