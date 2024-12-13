package com.example.currency_demo.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.currency_demo.data.model.CurrencyInfo


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyDiffCallback, v 0.1 Fri 12/13/2024 8:13 PM by Houwen Lie
 */
object CurrencyDiffCallback : DiffUtil.ItemCallback<CurrencyInfo>() {

    override fun areItemsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
        return oldItem == newItem
    }
}