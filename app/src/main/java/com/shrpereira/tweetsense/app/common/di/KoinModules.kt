package com.shrpereira.tweetsense.app.common.di

import com.shrpereira.tweetsense.data.cache.authentication.AccessTokenDataSource
import com.shrpereira.tweetsense.data.cache.authentication.AccessTokenDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val uiModule = module(override = true) {

}

val domainModule = module(override = true) {

}

val dataModule = module(override = true) {

	single { AccessTokenDataSourceImpl(androidContext()) as AccessTokenDataSource }
}