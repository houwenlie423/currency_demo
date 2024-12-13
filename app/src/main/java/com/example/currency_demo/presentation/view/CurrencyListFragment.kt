package com.example.currency_demo.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currency_demo.databinding.FragmentCurrencyListBinding
import com.example.currency_demo.presentation.adapter.CurrencyAdapter
import com.example.currency_demo.presentation.state.CurrencyListState
import com.example.currency_demo.presentation.utils.showToast
import com.example.currency_demo.presentation.viewmodel.CurrencyListViewModel
import com.example.currency_demo.presentation.viewmodel.DemoViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CurrencyListFragment, v 0.1 Fri 12/13/2024 7:44 PM by Houwen Lie
 */
@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    private var _binding: FragmentCurrencyListBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<CurrencyListViewModel>()

    private val currencyAdapter = CurrencyAdapter()

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
        setupRecyclerView()
        observeViewState()
    }

    private fun setupRecyclerView() {
        binding.rvCurrencies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = currencyAdapter
        }
    }

    private fun observeViewState() {
        viewModel.state.observe(viewLifecycleOwner) { state -> render(state) }
    }

    private fun render(state: CurrencyListState) {
        when (state) {
            is CurrencyListState.CurrenciesUpdated -> {
                binding.rvCurrencies.isVisible = true
                currencyAdapter.submitList(state.currencies)
            }

            is CurrencyListState.Error -> activity?.showToast("SOMETHING BAD")

            else -> Unit
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}