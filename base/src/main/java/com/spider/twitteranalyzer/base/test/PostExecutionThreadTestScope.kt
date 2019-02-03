package com.spider.twitteranalyzer.base.test

import com.spider.twitteranalyzer.base.domain.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class PostExecutionThreadTestScope : PostExecutionThread {
    override fun getScheduler(): Scheduler {
        return Schedulers.trampoline()
    }
}