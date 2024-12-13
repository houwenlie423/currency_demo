package com.example.currency_demo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.currency_demo.data.model.CurrencyInfo
import com.example.currency_demo.databinding.ItemCurrencyInfoBinding


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyAdapter, v 0.1 Fri 12/13/2024 8:16 PM by Houwen Lie
 */
class CurrencyAdapter : ListAdapter<CurrencyInfo, CurrencyViewHolder>(CurrencyDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = ItemCurrencyInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currencyInfo = getItem(position)
        holder.bind(currencyInfo)
    }
}