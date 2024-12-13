package com.example.currency_demo.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version RxLifecycleVM, v 0.1 Fri 12/13/2024 8:23 PM by Houwen Lie
 */
abstract class RxLifecycleVM<S, E> : ViewModel() {

    protected val disposeBag by lazy { CompositeDisposable() }

    abstract val state: LiveData<S>

    abstract fun dispatchEvent(event: E)

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }
}