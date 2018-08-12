package bloder.com.twitter

import android.app.Application
import bloder.com.twitter.di.domainModule
import bloder.com.twitter.di.presentationModule
import bloder.com.twitter.di.uiModule
import org.koin.android.ext.android.startKoin

class TwitterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(uiModule, presentationModule, domainModule))
    }
}