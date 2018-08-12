package bloder.com.twitter.di

import bloder.com.twitter.preferences.AuthTokenPreferences
import org.koin.dsl.module.applicationContext

val uiModule = applicationContext {
    bean { AuthTokenPreferences() }
}