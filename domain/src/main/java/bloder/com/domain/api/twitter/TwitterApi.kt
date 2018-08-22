package bloder.com.domain.api.twitter

import bloder.com.domain.BuildConfig
import bloder.com.domain.api.Api

class TwitterApi : Api<TwitterServices>() {

    override fun service(): TwitterServices = retrofit(BuildConfig.TWITTER_API)
            .create(TwitterServices::class.java)
}