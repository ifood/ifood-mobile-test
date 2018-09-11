package com.rlino.ifoodtwitterchallenge.domain.twitter

import com.rlino.ifoodtwitterchallenge.data.twitter.TwitterRepository
import com.rlino.ifoodtwitterchallenge.domain.SingleUseCase
import com.rlino.ifoodtwitterchallenge.domain.invoke
import com.rlino.ifoodtwitterchallenge.model.Tweets
import com.rlino.ifoodtwitterchallenge.retryWhenWithLimit
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class FetchTweetsUseCase @Inject constructor(
        private val twitterRepository: TwitterRepository,
        private val authUseCase: AuthUseCase
) : SingleUseCase<String, Tweets>() {

    override fun execute(parameters: String): Single<Tweets> {
        return twitterRepository.getTweetsFromUser(parameters)
                .retryWhenWithLimit(3) {
                    if(it is HttpException && it.code() == 400)
                        authUseCase().toFlowable().observeOn(Schedulers.io())
                    else
                        Flowable.error(it)
                }
    }

}