package com.rlino.ifoodtwitterchallenge.domain.twitter

import com.rlino.ifoodtwitterchallenge.data.sharedprefs.TwitterSharedPrefs
import com.rlino.ifoodtwitterchallenge.data.twitter.TwitterRepository
import com.rlino.ifoodtwitterchallenge.domain.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class AuthUseCase @Inject constructor(
        private val twitterRepository: TwitterRepository,
        private val twitterSharedPrefs: TwitterSharedPrefs
) : SingleUseCase<Unit, String>() {

    override fun execute(parameters: Unit): Single<String> {
        return twitterRepository.getTwitterToken()
                .doOnSubscribe { twitterSharedPrefs.updateToken("") }
                .map {
                    twitterSharedPrefs.updateToken(it)
                    it
                }
    }

}
