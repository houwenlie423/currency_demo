package com.example.currency_demo.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.currency_demo.R
import com.example.currency_demo.databinding.ActivityDemoBinding

class DemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCurrencyListFragment()
    }

    private fun initCurrencyListFragment() {
        supportFragmentManager.commit { replace(R.id.fragment_container, CurrencyListFragment()) }
    }
}