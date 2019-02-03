package com.spider.twitteranalyzer.base.extension

import android.view.View

fun View.show(show: Boolean) {
    this.visibility = if (show) {
        View.VISIBLE
    } else {
        View.GONE
    }
}