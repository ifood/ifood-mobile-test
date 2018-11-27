package com.eblushe.apptwitter.common.di

import com.eblushe.apptwitter.common.apis.twitter.services.OAuthService
import com.eblushe.apptwitter.common.apis.twitter.services.StatusesEndpoint
import com.eblushe.apptwitter.common.apis.twitter.services.StatusesService
import com.eblushe.apptwitter.common.providers.*
import com.eblushe.apptwitter.common.repositories.AuthRepository
import com.eblushe.apptwitter.common.repositories.BaseRepository
import com.eblushe.apptwitter.common.repositories.StatusesRepository
import com.eblushe.apptwitter.features.home.viewmodels.HomeViewModel
import com.eblushe.apptwitter.features.splash.viewmodels.SplashViewModel
import com.eblushe.apptwitter.features.userdetails.viewmodels.UserDetailsViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val repositoriesModule = module {
    single { BaseRepository(get(), get(), get()) }
    single { AuthRepository(get(), get(), get(), get()) }
    single { StatusesRepository(get(), get(), get(), get(), get()) }
}

val storageModule = module {
    single { StorageProvider }
    single { StorageProvider.getDb().userDao() }
    single { StorageProvider.getDb().tweetDao() }
}

val apiModule = module {
    single { ApiProvider }
    single { OAuthService(get()) }
    single { StatusesService(get()) }
}

val featuresModule = module {
    viewModel { HomeViewModel(rxProvider = get()) }
    viewModel { UserDetailsViewModel(statusesRepository = get(), rxProvider = get()) }
    viewModel { SplashViewModel(authRepository = get(), rxProvider = get()) }
}

val rxModule = module {
    single { SchedulerProvider() as RxProvider }
}

val routerModule = module {
    single { RouterProvider }
}

val mockModule = module {
    single(override = true) { SchedulerProviderMock() as RxProvider }
}

val diModule = listOf(
    routerModule,
    featuresModule,
    repositoriesModule,
    storageModule,
    apiModule,
    rxModule
)