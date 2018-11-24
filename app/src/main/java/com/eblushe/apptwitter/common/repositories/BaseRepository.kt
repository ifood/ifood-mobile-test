package com.eblushe.apptwitter.common.repositories

import com.eblushe.apptwitter.common.providers.ApiProvider
import com.eblushe.apptwitter.common.providers.RxProvider
import com.eblushe.apptwitter.common.providers.StorageProvider

open class BaseRepository(
    val apiProvider: ApiProvider,
    val storageProvider: StorageProvider,
    var schedulerProvider: RxProvider
)