package com.eblushe.apptwitter.common.di

import com.eblushe.apptwitter.common.apis.twitter.services.OAuthService
import com.eblushe.apptwitter.common.providers.*
import com.eblushe.apptwitter.common.repositories.AuthRepository
import com.eblushe.apptwitter.common.repositories.BaseRepository
import com.eblushe.apptwitter.features.home.viewmodels.HomeViewModel
import com.eblushe.apptwitter.features.splash.viewmodels.SplashViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val repositoriesModule = module {
    single { BaseRepository(get(), get(), get()) }
    single { AuthRepository(get(), get(), get(), get()) }
}

val storageModule = module {
    single { StorageProvider }
}

val apiModule = module {
    single { ApiProvider }
    single { OAuthService(get()) }
}

val featuresModule = module {
    viewModel { HomeViewModel(rxProvider = get()) }
    viewModel { SplashViewModel( authRepository = get(), rxProvider = get()) }
}

val rxModule = module {
    single { SchedulerProvider() as RxProvider }
}

val routerModule = module {
    single { RouterProvider }
}

val diModule = listOf(
    routerModule,
    featuresModule,
    repositoriesModule,
    storageModule,
    apiModule,
    rxModule
)