package com.yotalabs.koral.data

import io.reactivex.Scheduler

interface Schedulers {

    fun single(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler

    fun mainThread(): Scheduler
}
