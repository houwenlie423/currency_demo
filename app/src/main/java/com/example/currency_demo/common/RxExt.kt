package com.example.currency_demo.common

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version RxExt, v 0.1 Sat 12/14/2024 10:39 AM by Houwen Lie
 */

fun <T> Observable<T>.collectBy(bag: CompositeDisposable) = subscribe().addTo(bag)

fun <T> Single<T>.collectBy(bag: CompositeDisposable) = subscribe().addTo(bag)

fun Completable.collectBy(bag: CompositeDisposable) = subscribe().addTo(bag)