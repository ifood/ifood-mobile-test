package com.eblushe.apptwitter.common.repositories

import com.eblushe.apptwitter.common.apis.twitter.mapToTweet
import com.eblushe.apptwitter.common.apis.twitter.services.StatusesService
import com.eblushe.apptwitter.common.models.Tweet
import com.eblushe.apptwitter.common.providers.ApiProvider
import com.eblushe.apptwitter.common.providers.RxProvider
import com.eblushe.apptwitter.common.providers.StorageProvider
import io.reactivex.Single

class StatusesRepository(
    var statusesService: StatusesService,
    apiProvider: ApiProvider,
    storageProvider: StorageProvider,
    schedulerProvider: RxProvider
    ) : BaseRepository(apiProvider, storageProvider, schedulerProvider) {

    fun getUserTimeLine(screenName: String): Single<List<Tweet>> {
        var observable: Single<List<Tweet>>?

        observable = statusesService.getUserTimeLine(screenName)
            .subscribeOn(schedulerProvider.newThread())
            .observeOn(schedulerProvider.mainThread())
            .map { items -> items.map(::mapToTweet) }
        return observable
    }
}