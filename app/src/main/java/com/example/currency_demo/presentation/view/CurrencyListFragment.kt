package com.example.currency_demo.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currency_demo.R
import com.example.currency_demo.databinding.FragmentCurrencyListBinding
import com.example.currency_demo.presentation.adapter.CurrencyAdapter
import com.example.currency_demo.presentation.event.CurrencyListEvent
import com.example.currency_demo.presentation.model.CurrencyUiModel
import com.example.currency_demo.presentation.state.CurrencyListState
import com.example.currency_demo.presentation.utils.onQueryTextChanged
import com.example.currency_demo.presentation.utils.showToast
import com.example.currency_demo.presentation.viewmodel.CurrencyListViewModel
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
        setupToolbarMenu()
        observeViewState()
    }

    private fun setupToolbarMenu() {
        val menuHost = activity as? MenuHost ?: return
        val menuProvider = createMenuProvider()
        menuHost.addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupRecyclerView() {
        binding.rvCurrencies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = currencyAdapter
            isVisible = true
        }
    }

    private fun observeViewState() {
        viewModel.state.observe(viewLifecycleOwner) { state -> render(state) }
    }

    private fun render(state: CurrencyListState) {
        when (state) {
            is CurrencyListState.CurrenciesUpdated -> showCurrencies(state.currencies)

            is CurrencyListState.CurrenciesNotFound -> showEmptyState()

            is CurrencyListState.Error -> showErrorToast(state.errorMessage)
        }
    }

    private fun showCurrencies(currencies: List<CurrencyUiModel>) {
        binding.apply {
            currencyAdapter.submitList(currencies)
            vEmptyState.root.isVisible = false
        }
    }

    private fun showEmptyState() {
        binding.apply {
            currencyAdapter.submitList(emptyList())
            vEmptyState.root.isVisible = true
        }
    }

    private fun showErrorToast(errorMessage: String) {
        val message = getString(R.string.general_error_message, errorMessage)
        activity?.showToast(message)
    }

    private fun createMenuProvider() = object : MenuProvider {

        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.menu_fragment_currency_list, menu)

            val searchItem = menu.findItem(R.id.action_search)
            val searchView = searchItem.actionView as? SearchView ?: return

            searchView.onQueryTextChanged { searchQuery ->
                viewModel.dispatchEvent(CurrencyListEvent.SearchQueryUpdated(searchQuery))
            }

        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return menuItem.itemId == R.id.action_search
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}