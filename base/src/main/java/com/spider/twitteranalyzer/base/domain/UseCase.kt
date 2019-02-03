package com.spider.twitteranalyzer.base.domain

import android.arch.lifecycle.LiveData

interface UseCase<T> {

    fun getLiveData(): LiveData<T>

    fun cleanUp()
}
