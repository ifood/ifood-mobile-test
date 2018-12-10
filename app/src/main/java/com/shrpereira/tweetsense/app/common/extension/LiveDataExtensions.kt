package com.shrpereira.tweetsense.app.common.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
	liveData.observe(this, Observer { it?.let { value -> action(value) } })
}

fun <T> LifecycleOwner.observeNullable(liveData: LiveData<T>, action: (t: T?) -> Unit) {
	liveData.observe(this, Observer { value -> action(value) })
}