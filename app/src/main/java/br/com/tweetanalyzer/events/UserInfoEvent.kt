package br.com.tweetanalyzer.events

import br.com.tweetanalyzer.models.TwitterUserInfo

/**
 * Created by gabrielsamorim
 * on 20/05/18.
 */
data class UserInfoEvent(val user: TwitterUserInfo?)