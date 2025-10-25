package com.perrystreet.nobigviewmodels.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.annotation.Single

@Single
class SchedulerProvider : ISchedulerProvider {
    override val mainScheduler: Scheduler = AndroidSchedulers.mainThread()
    override val databaseScheduler: Scheduler = Schedulers.single()
    override val ioScheduler: Scheduler = Schedulers.io()
    override val computationScheduler: Scheduler = Schedulers.computation()
}

interface ISchedulerProvider {
    val mainScheduler: Scheduler
    val databaseScheduler: Scheduler
    val ioScheduler: Scheduler
    val computationScheduler: Scheduler
}
