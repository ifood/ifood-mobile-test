package bloder.com.twitter

import android.app.Application
import bloder.com.twitter.di.domainModule
import bloder.com.twitter.di.presentationModule
import org.koin.android.ext.android.startKoin

class TwitterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(presentationModule, domainModule))
    }
}