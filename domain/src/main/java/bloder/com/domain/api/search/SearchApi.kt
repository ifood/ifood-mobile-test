package bloder.com.domain.api.search

import bloder.com.domain.BuildConfig
import bloder.com.domain.api.Api

class SearchApi : Api<SearchServices>() {

    override fun service(): SearchServices = retrofit(BuildConfig.TWITTER_API)
            .create(SearchServices::class.java)
}