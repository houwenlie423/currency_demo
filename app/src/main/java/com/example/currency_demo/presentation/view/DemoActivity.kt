package com.example.currency_demo.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.currency_demo.R
import com.example.currency_demo.databinding.ActivityDemoBinding
import com.example.currency_demo.presentation.event.DemoEvent
import com.example.currency_demo.presentation.state.DemoViewState
import com.example.currency_demo.presentation.utils.hideKeyboard
import com.example.currency_demo.presentation.utils.onTextChanged
import com.example.currency_demo.presentation.utils.showToast
import com.example.currency_demo.presentation.viewmodel.DemoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDemoBinding

    private val viewModel by viewModels<DemoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCurrencyListFragment()
        initListeners()
        observeViewState()
    }

    private fun initCurrencyListFragment() {
        supportFragmentManager.commit { replace(R.id.fragment_container, CurrencyListFragment()) }
    }

    private fun initListeners() {
        binding.apply {
            btnAddCrpyto.setOnClickListener { viewModel.dispatchEvent(DemoEvent.AddCryptoButtonClicked) }
            btnAddFiat.setOnClickListener { viewModel.dispatchEvent(DemoEvent.AddFiatButtonClicked) }
            btnAddNew.setOnClickListener { viewModel.dispatchEvent(DemoEvent.AddCustomCurrencyButtonClicked) }
            btnClear.setOnClickListener { viewModel.dispatchEvent(DemoEvent.ClearButtonClicked) }

            etCurrencyId.onTextChanged { id ->
                viewModel.dispatchEvent(
                    DemoEvent.CurrencyIdChanged(id)
                )
            }
            etCurrencyName.onTextChanged { name ->
                viewModel.dispatchEvent(
                    DemoEvent.CurrencyNameChanged(name)
                )
            }
            etCurrencySymbol.onTextChanged { symbol ->
                viewModel.dispatchEvent(
                    DemoEvent.CurrencySymbolChanged(symbol)
                )
            }
            etCurrencyCode.onTextChanged { code ->
                viewModel.dispatchEvent(
                    DemoEvent.CurrencyCodeChanged(code)
                )
            }
        }
    }

    private fun observeViewState() {
        viewModel.state.observe(this) { state -> render(state) }
    }

    private fun render(state: DemoViewState) {
        when (state) {
            is DemoViewState.AddCustomCurrencySuccess -> {
                showToast(getString(R.string.add_custom_currency_success, state.currencyName))
                resetTextFields()
                binding.root.hideKeyboard()
            }

            is DemoViewState.ClearDataSuccess -> showToast(getString(R.string.clear_data_success))

            is DemoViewState.ValidationError -> showToast(getString(R.string.validation_error_message))

            is DemoViewState.GeneralError -> showToast(getString(R.string.general_error_message, state.errorMessage))
        }
    }

    private fun resetTextFields() {
        binding.apply {
            etCurrencyId.setText("")
            etCurrencyName.setText("")
            etCurrencySymbol.setText("")
            etCurrencyCode.setText("")
        }
    }
}