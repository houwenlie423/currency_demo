package com.example.currency_demo.common.scheduler

import io.reactivex.Scheduler


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version SchedulerProvider, v 0.1 Thu 12/12/2024 8:26 PM by Houwen Lie
 */
interface SchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}