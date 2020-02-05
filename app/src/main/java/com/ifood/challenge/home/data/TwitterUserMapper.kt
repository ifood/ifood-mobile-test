package com.ifood.challenge.home.data

import com.ifood.challenge.base.data.BaseMapper
import com.ifood.challenge.home.model.TwitterUser
import timber.log.Timber
import javax.inject.Inject

class TwitterUserMapper @Inject constructor() :
    BaseMapper<List<TwitterUserRaw>, List<TwitterUser>>() {
    override val trackException: (Throwable) -> Unit
        get() = { error ->
            Timber.e(error, "Track error on Crashlytics")
        }

    override fun assertEssentialParams(raw: List<TwitterUserRaw>) {
        raw.forEach { userRaw ->
            if (userRaw.id == null) addMissingParam("twitterUserId")
            if (userRaw.name.isNullOrBlank()) addMissingParam("twitterUserName")
            if (userRaw.screenName.isNullOrBlank()) addMissingParam("twitterUserScreenName")
            if (userRaw.profileImageUrlHttps.isNullOrBlank()) addMissingParam("twitterUserProfileImageUrl")
        }
    }

    override fun copyValues(raw: List<TwitterUserRaw>): List<TwitterUser> =
        raw.map { userRaw ->
            TwitterUser(
                id = userRaw.id!!,
                name = userRaw.name!!,
                screenName = userRaw.screenName!!,
                location = userRaw.location ?: "",
                profileImageUrlHttps = userRaw.profileImageUrlHttps!!
            )
        }
}
