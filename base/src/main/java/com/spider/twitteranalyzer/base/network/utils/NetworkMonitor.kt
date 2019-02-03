package com.spider.twitteranalyzer.base.network.utils

import android.content.Context
import android.net.ConnectivityManager


interface NetworkMonitor {
    val isConnected: Boolean

    class Impl(val context: Context) : NetworkMonitor {
        override val isConnected: Boolean
            get() = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                    .activeNetworkInfo?.isConnectedOrConnecting ?: false
    }
}

