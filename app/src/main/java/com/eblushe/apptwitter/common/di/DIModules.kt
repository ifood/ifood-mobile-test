package com.eblushe.apptwitter.common.di

import com.eblushe.apptwitter.common.providers.*
import com.eblushe.apptwitter.common.repositories.BaseRepository
import com.eblushe.apptwitter.features.home.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val repositoriesModule = module {
    single { BaseRepository(get(), get(), get()) }
}

val storageModule = module {
    single { StorageProvider }
}

val apiModule = module {
    single { ApiProvider }
}

val featuresModule = module {
    viewModel { HomeViewModel() }
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