@file:Suppress("unused")

package com.shrpereira.tweetsense.app

import android.app.Application
import com.shrpereira.tweetsense.app.common.di.dataModule
import com.shrpereira.tweetsense.app.common.di.domainModule
import com.shrpereira.tweetsense.app.common.di.uiModule
import org.koin.android.ext.android.startKoin

class TweetSenseApplication : Application() {

	private val modulesList = listOf(uiModule, domainModule, dataModule)

	override fun onCreate() {
		super.onCreate()

		// Starting dependency injection
		startKoin(this, modulesList)
	}
}