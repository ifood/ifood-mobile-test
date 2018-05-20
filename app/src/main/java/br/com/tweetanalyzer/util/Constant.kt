package br.com.tweetanalyzer.util

/**
 * Created by gabrielsamorim
 * on 15/05/18.
 */

enum class JobType {
    UNKNOWN,
    GET_AUTH,
    GET_USER_INFO,
    SEARCH_INPUT,
    ANALYSE_SENTIMENT
}

class Constant {

    companion object {
        const val JOB_TYPE = "job.type"

        //Search Type
        const val SEARCH_USER_INPUT = "search.user.input"
        const val SEARCH_INPUT = "search.user.tweets"
        const val ANALYSE_SENTIMENT = "search.user.analyse.sentiment"
    }
}