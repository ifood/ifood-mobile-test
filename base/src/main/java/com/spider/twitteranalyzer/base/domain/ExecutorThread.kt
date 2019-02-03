package com.spider.twitteranalyzer.base.domain

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

interface ExecutorThread {
    fun getScheduler(): Scheduler

    class Impl : ExecutorThread {
        override fun getScheduler(): Scheduler {
            return Schedulers.io()
        }
    }
}