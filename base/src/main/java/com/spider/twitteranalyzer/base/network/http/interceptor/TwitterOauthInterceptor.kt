package com.spider.twitteranalyzer.base.network.http.interceptor

import com.spider.twitteranalyzer.base.network.api.twitter.TwitterOAuthApi
import com.spider.twitteranalyzer.base.network.http.TokenStorage
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class TwitterOauthInterceptor constructor(
        private val tokenStorage: TokenStorage,
        private val api: TwitterOAuthApi,
        lock: Any? = null
) : Interceptor {

    private val lock = lock ?: this

    override fun intercept(chain: Interceptor.Chain): Response = chain.request().newBuilder()
            .apply {
                header("Accept", "application/json")
                header("Authorization", "Bearer ${tokenStorage.token}")
            }.let { builder ->
                builder.build().let { request ->
                    val response = chain.proceed(request)
                    return synchronized(lock) {
                        when {
                            response.code() == 401 -> refreshToken(request, chain)
                            else -> response
                        }
                    }
                }
            }

    private fun retryWithNewToken(
            request: Request,
            accessToken: String?,
            chain: Interceptor.Chain
    ): Response =
            request.newBuilder().apply {
                header("Authorization", "Bearer $accessToken")
            }.let { newRequest ->
                chain.proceed(newRequest.build())
            }

    private fun refreshToken(request: Request, chain: Interceptor.Chain): Response {
        return api.refreshTwitterToken().execute().let {
            if (it.isSuccessful) {
                it.body()?.accessToken?.let { accessToken ->
                    tokenStorage.token = accessToken
                }
            }
            retryWithNewToken(request, tokenStorage.token, chain)
        }
    }


}