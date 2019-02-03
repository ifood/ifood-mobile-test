package com.spider.twitteranalyzer.base.test

import com.spider.twitteranalyzer.base.domain.ExecutorThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class ExecutorThreadTestScope : ExecutorThread {
    override fun getScheduler(): Scheduler {
        return Schedulers.trampoline()
    }
}