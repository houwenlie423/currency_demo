package com.example.currency_demo.common.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import java.lang.AssertionError
import java.util.concurrent.TimeUnit


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version FakeSchedulerProvider, v 0.1 Fri 12/13/2024 9:40 PM by Houwen Lie
 */
class FakeSchedulerProvider : SchedulerProvider {

    private val testScheduler = TestScheduler()

    private var isImmediate = true

    private var computationInvocationCount = 0

    private var ioInvocationCount = 0

    private var uiInvocationCount = 0

    override fun computation(): Scheduler {
        computationInvocationCount++
        return if (isImmediate) Schedulers.trampoline() else testScheduler
    }

    override fun io(): Scheduler {
        ioInvocationCount++
        return if (isImmediate) Schedulers.trampoline() else testScheduler
    }

    override fun ui(): Scheduler {
        uiInvocationCount++
        return if (isImmediate) Schedulers.trampoline() else testScheduler
    }

    fun immediate() {
        isImmediate = true
    }

    fun test() {
        isImmediate = false
    }

    fun reset() {
        isImmediate = true
        computationInvocationCount = 0
        ioInvocationCount = 0
        uiInvocationCount = 0
    }

    fun advanceTimeBy(delay: Long, timeUnit: TimeUnit) {
        testScheduler.advanceTimeBy(delay, timeUnit)
    }

    fun verifyComputation(count: Int = 1) {
        if (computationInvocationCount != count) {
            throw AssertionError("Computation Scheduler was not invoked $count times")
        }
    }

    fun verifyIO(count: Int) {
        if (ioInvocationCount != count) {
            throw AssertionError("IO Scheduler was not invoked $count times")
        }
    }

    fun verifyUI(count: Int) {
        if (uiInvocationCount != count) {
            throw AssertionError("UI Scheduler was not invoked $count times")
        }
    }
}