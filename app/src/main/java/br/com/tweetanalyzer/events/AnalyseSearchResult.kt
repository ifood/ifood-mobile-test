package br.com.tweetanalyzer.events

import br.com.tweetanalyzer.models.TweetAnalyseResult
import br.com.tweetanalyzer.models.TwitterModel

/**
 * Created by gabrielsamorim
 * on 19/05/18.
 */
data class AnalyseSearchResult(val result: TweetAnalyseResult?, val item: TwitterModel)