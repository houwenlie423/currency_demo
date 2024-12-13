package com.example.currency_demo.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.currency_demo.R
import com.example.currency_demo.databinding.ActivityDemoBinding
import com.example.currency_demo.presentation.event.DemoEvent
import com.example.currency_demo.presentation.state.DemoViewState
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
            btnAddCrpyto.setOnClickListener {
                viewModel.dispatchEvent(DemoEvent.AddCryptoButtonClicked(emptyList()))
            }
        }
    }

    private fun observeViewState() {
        viewModel.state.observe(this) { state -> render(state) }
    }

    private fun render(state: DemoViewState) {
        when (state) {
            is DemoViewState.AddCryptoCurrenciesSuccess -> showToast("Success adding crypto currencies")

            is DemoViewState.AddCryptoCurrenciesError -> showToast(state.errorMessage)

            else -> Unit
        }
    }
}