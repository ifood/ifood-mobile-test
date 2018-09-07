package com.rlino.ifoodtwitterchallenge.domain.twitter

import com.rlino.ifoodtwitterchallenge.data.twitter.TwitterRepository
import com.rlino.ifoodtwitterchallenge.domain.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class AuthUseCase @Inject constructor(
        val twitterRepository: TwitterRepository
) : SingleUseCase<Unit, String>() {

    override fun execute(parameters: Unit): Single<String> {
        return twitterRepository.getTwitterToken()
    }

}


class AuthUseCaseParameters()
