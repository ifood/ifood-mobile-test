package com.spider.twitteranalyzer.base.domain

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

interface PostExecutionThread {
    fun getScheduler(): Scheduler

    class Impl : PostExecutionThread {
        override fun getScheduler(): Scheduler {
            return AndroidSchedulers.mainThread()
        }
    }
}