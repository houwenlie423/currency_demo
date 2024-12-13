package com.example.currency_demo.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.currency_demo.databinding.FragmentCurrencyListBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyListFragment, v 0.1 Fri 12/13/2024 7:44 PM by Houwen Lie
 */
@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    private var _binding: FragmentCurrencyListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}