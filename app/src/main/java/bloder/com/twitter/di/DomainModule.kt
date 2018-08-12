package bloder.com.twitter.di

import bloder.com.domain.interactor.TwitterInteractor
import org.koin.dsl.module.applicationContext

val domainModule = applicationContext {
    factory { TwitterInteractor() }
}