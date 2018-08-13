package bloder.com.domain.api.sentiment

import bloder.com.domain.BuildConfig
import bloder.com.domain.api.Api


class SentimentApi : Api<SentimentServices>() {

    override fun service(): SentimentServices = retrofit(BuildConfig.GOOGLE_LANGUAGE_API)
            .create(SentimentServices::class.java)
}