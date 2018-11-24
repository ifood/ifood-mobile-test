package com.eblushe.apptwitter.common.repositories

import com.eblushe.apptwitter.common.apis.twitter.mapToOAuthToken
import com.eblushe.apptwitter.common.apis.twitter.services.OAuthService
import com.eblushe.apptwitter.common.models.OAuthToken
import com.eblushe.apptwitter.common.providers.ApiProvider
import com.eblushe.apptwitter.common.providers.RxProvider
import com.eblushe.apptwitter.common.providers.StorageProvider
import io.reactivex.Single

class AuthRepository(
    var oAuthService: OAuthService,
    apiProvider: ApiProvider,
    storageProvider: StorageProvider,
    schedulerProvider: RxProvider
    ) : BaseRepository(apiProvider, storageProvider, schedulerProvider) {

    fun requestAuthToken() : Single<OAuthToken>? {
        var observable: Single<OAuthToken>? = null

        storageProvider.readPreference { preferences ->
            val key= OAuthToken.ACCESS_TOKEN_TAG
            preferences.getString(key, null)?.let {
                observable = Single.just(OAuthToken(it))
            }
        }

        val noCached = observable == null
        if (noCached) {
            val grantType = "client_credentials"
            observable = oAuthService.requestToken(grantType)
                .subscribeOn(schedulerProvider.newThread())
                .observeOn(schedulerProvider.mainThread())
                .map(::mapToOAuthToken)
                .doAfterSuccess { oauthToken ->
                    storageProvider.writePreference { editor ->
                        editor.putString(OAuthToken.ACCESS_TOKEN_TAG, oauthToken.accessToken)
                    }
                }
        }

        return observable
    }
}