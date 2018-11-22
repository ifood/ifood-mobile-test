package com.eblushe.apptwitter.common.di

import com.eblushe.appinstagram.common.providers.ApiProvider
import com.eblushe.apptwitter.common.providers.RouterProvider
import com.eblushe.apptwitter.common.providers.RxProvider
import com.eblushe.apptwitter.common.providers.SchedulerProvider
import com.eblushe.apptwitter.common.providers.StorageProvider
import com.eblushe.apptwitter.common.repositories.BaseRepository
import com.eblushe.apptwitter.features.home.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val RepositoriesModule = module {
    single { BaseRepository(get(), get(), get()) }
}

val StorageModule = module {
    single { StorageProvider }
}

val ApiModule = module {
    single { ApiProvider }
}

val FeaturesModule = module {
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
    FeaturesModule,
    RepositoriesModule,
    StorageModule,
    ApiModule,
    rxModule
)