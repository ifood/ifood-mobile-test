package com.rlino.ifoodtwitterchallenge.domain.twitter

import com.rlino.ifoodtwitterchallenge.data.twitter.TwitterRepository
import com.rlino.ifoodtwitterchallenge.defaultSchedulers
import com.rlino.ifoodtwitterchallenge.domain.SingleUseCase
import com.rlino.ifoodtwitterchallenge.logErrors
import com.rlino.ifoodtwitterchallenge.model.Tweets
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import retrofit2.HttpException
import javax.inject.Inject

class FetchTweetsUseCase @Inject constructor(
        private val twitterRepository: TwitterRepository,
        private val authUseCase: AuthUseCase
) : SingleUseCase<String, Tweets>() {

    override fun execute(parameters: String): Single<Tweets> {
        return twitterRepository.getTweetsFromUser(parameters)
                .defaultSchedulers()
                .logErrors()
                .retryWhen { attempts ->
                    attempts.zipWith(Flowable.range(1, 3), BiFunction<Throwable, Int, Throwable> { t1, _ ->
                        return@BiFunction t1
                    }).flatMap {
                        if(it is HttpException && (it.code() == 403 || it.code() == 401))
                            authUseCase(Unit).toFlowable()
                        else
                            Flowable.error(it)
                    }
                }

    }

}