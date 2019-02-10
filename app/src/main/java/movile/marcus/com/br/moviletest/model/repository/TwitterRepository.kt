package movile.marcus.com.br.moviletest.model.repository

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import movile.marcus.com.br.moviletest.model.data.TweetData
import movile.marcus.com.br.moviletest.model.remote.TwitterApi
import javax.inject.Inject

class TwitterRepository @Inject constructor(
    private val twitterApi: TwitterApi
) {

    fun getTweetByUser(user: String): Flowable<List<TweetData>> {
        return twitterApi.getTweetsByUsername(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}