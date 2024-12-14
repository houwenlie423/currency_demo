package com.example.currency_demo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.currency_demo.common.RxLifecycleVM
import com.example.currency_demo.common.scheduler.SchedulerProvider
import com.example.currency_demo.data.model.CurrencyInfo
import com.example.currency_demo.domain.repository.CurrencyRepository
import com.example.currency_demo.presentation.event.DemoEvent
import com.example.currency_demo.presentation.state.DemoViewState
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version DemoViewModel, v 0.1 Fri 12/13/2024 8:37 PM by Houwen Lie
 */
@HiltViewModel
class DemoViewModel @Inject constructor(

) : RxLifecycleVM<DemoViewState, DemoEvent>() {

    private val _state = MutableLiveData<DemoViewState>()

    override val state: LiveData<DemoViewState> get() = _state

    override fun dispatchEvent(event: DemoEvent) {
        when(event) {

        }
    }

}