package com.shrpereira.tweetsense.data.remote

import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory
import com.google.gson.GsonBuilder
import com.shrpereira.tweetsense.data.cache.authentication.AccessTokenDataSource
import okhttp3.Cache
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RetrofitBuilder(
	private val baseUrl: String,
	private val cacheDir: File,
	private val apiType: ApiType
) {

	private var cacheSize = 10 * 1024 * 1024 // 10 MB
	private var retrofit: Retrofit? = null

	fun <T> build(service: Class<T>): T {
		if (retrofit == null) {

			retrofit = Retrofit.Builder()
				.baseUrl(baseUrl)
				.client(httpClient)
				.addCallAdapterFactory(LiveDataCallAdapterFactory.create())
				.addConverterFactory(LiveDataResponseBodyConverterFactory.create())
				.addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
				.build()
		}
		// Non-null asserted because of the previous check
		return this.retrofit!!.create(service)
	}

	private val httpClient: OkHttpClient
		get() {
			val builder = OkHttpClient().newBuilder()
			builder.readTimeout(60, TimeUnit.SECONDS)
			builder.connectTimeout(60, TimeUnit.SECONDS)

			val httpCacheDirectory = File(cacheDir, "http-cache")
			val cache = Cache(httpCacheDirectory, cacheSize.toLong())
			builder.cache(cache)

			builder.addInterceptor { chain ->
				val requestBuilder = chain.request().newBuilder().apply {
					addAuthorizationHeaders(apiType)
					addHeader("Content-Type", "application/json; charset=utf-8")
				}

				chain.proceed(requestBuilder.build())
			}

			return builder.build()
		}

	private fun Request.Builder.addAuthorizationHeaders(apiType: ApiType) {
		when (apiType) {
			is ApiType.TwitterAPI -> {
				val authorization = apiType.authTokenSource.getTwitterAccessToken()
					?: Credentials.basic(apiType.apiKey, apiType.apiSecret)

				this.addHeader("Authorization", authorization)
			}
			is ApiType.GoogleAPI -> {
				apiType.authTokenSource.getGoogleAccessToken()?.let {
					this.addHeader("Authorization", "Bearer $it")
				}
			}
		}
	}

	sealed class ApiType {
		data class TwitterAPI(
			val apiKey: String,
			val apiSecret: String,
			val authTokenSource: AccessTokenDataSource
		) : ApiType()

		data class GoogleAPI(val authTokenSource: AccessTokenDataSource) : ApiType()
	}
}