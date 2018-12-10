package com.shrpereira.tweetsense.app.common.extension

fun <T> MutableList<T>.clearAndAddAll(list: List<T>) {
	this.apply {
		clear()
		addAll(list)
	}
}