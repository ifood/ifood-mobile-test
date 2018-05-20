package br.com.tweetanalyzer.util

/**
 * Created by gabrielsamorim
 * on 15/05/18.
 */
class Constant {
    companion object {
        const val JOB_TYPE = "job.type"

        //Job Type
        const val JOB_TYPE_GET_AUTH = 0
        const val JOB_GET_USER_INFO = 1
        const val JOB_TYPE_SEARCH_INPUT = 2
        const val JOB_ANALYSE_SENTIMENT = 3

        //Searche Type
        const val SEARCH_USER_INPUT = "search.user.input"
        const val SEARCH_INPUT = "search.user.tweets"
        const val ANALYSE_SENTIMENT = "search.user.analyse.sentiment"
    }
}