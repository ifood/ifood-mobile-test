package com.spider.twitteranalyzer.base.network.http.interceptor

import android.accounts.NetworkErrorException
import com.spider.twitteranalyzer.base.network.utils.NetworkMonitor
import okhttp3.Interceptor
import okhttp3.Response

class NetworkMonitorInterceptor constructor(private val networkMonitor: NetworkMonitor) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (networkMonitor.isConnected) {
            chain.proceed(chain.request())
        } else {
            throw NetworkErrorException()
        }
    }
}