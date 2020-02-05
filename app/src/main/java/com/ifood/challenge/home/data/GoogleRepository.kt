package com.ifood.challenge.home.data

import com.ifood.challenge.BuildConfig
import com.ifood.challenge.base.common.network.NetworkHandler
import com.ifood.challenge.base.data.BaseRemoteRepository
import com.ifood.challenge.home.model.Sentiment
import io.reactivex.Single
import javax.inject.Inject

interface GoogleRepository {

    fun analyzeSentiment(content: String): Single<Sentiment>

    class Remote @Inject constructor(
        private val googleService: GoogleService,
        private val sentimentMapper: SentimentMapper,
        networkHandler: NetworkHandler
    ) : BaseRemoteRepository(networkHandler), GoogleRepository {

        override fun analyzeSentiment(content: String): Single<Sentiment> =
            request(sentimentMapper) {
                val sentimentRequest = AnalyzeSentimentRequest(content)
                googleService.analyzeSentiment(
                    BuildConfig.GOOGLE_API_KEY,
                    sentimentRequest
                )
            }
    }
}
