package com.example.currency_demo.common.scheduler

import android.os.Looper
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version SchedulersProviderImpl, v 0.1 Thu 12/12/2024 8:30 PM by Houwen Lie
 */
class SchedulerProviderImpl(private val uiAsync: Boolean = true) : SchedulerProvider {

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.from(Looper.getMainLooper(), uiAsync)
    }
}