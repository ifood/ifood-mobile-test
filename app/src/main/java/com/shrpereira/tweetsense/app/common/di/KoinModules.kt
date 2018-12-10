package com.shrpereira.tweetsense.app.common.di

import com.shrpereira.tweetsense.app.BuildConfig
import com.shrpereira.tweetsense.data.cache.authentication.AccessTokenDataSource
import com.shrpereira.tweetsense.data.cache.authentication.AccessTokenDataSourceImpl
import com.shrpereira.tweetsense.data.remote.RetrofitBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val uiModule = module(override = true) {

}

val domainModule = module(override = true) {

}

val dataModule = module(override = true) {
	factory("TwitterRetrofitBuilder") {
		RetrofitBuilder(
			BuildConfig.TWITTER_API_URL,
			androidContext().cacheDir,
			RetrofitBuilder.ApiType.TwitterAPI(
				BuildConfig.TWITTER_API_KEY,
				BuildConfig.TWITTER_API_SECRET_KEY,
				get()
			)
		)
	}
	factory("GoogleRetrofitBuilder") {
		RetrofitBuilder(
			BuildConfig.GOOGLE_API_URL,
			androidContext().cacheDir,
			RetrofitBuilder.ApiType.GoogleAPI(get())
		)
	}

	single { AccessTokenDataSourceImpl(androidContext()) as AccessTokenDataSource }
}