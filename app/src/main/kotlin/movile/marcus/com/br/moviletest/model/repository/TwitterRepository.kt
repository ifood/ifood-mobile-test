package movile.marcus.com.br.moviletest.model.repository

import com.orhanobut.hawk.Hawk
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import movile.marcus.com.br.moviletest.model.api.TwitterApi
import movile.marcus.com.br.moviletest.model.data.TweetData
import javax.inject.Inject

class TwitterRepository @Inject constructor(
    private val twitterApi: TwitterApi
) {
    private val lastSearch = "last_search"

    fun getTweetByUser(user: String): Flowable<List<TweetData>> {
        return twitterApi.getTweetsByUsername(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun saveLastSearch(user: String) {
        Hawk.put(lastSearch, user)
    }

    fun getLastSearch(): String? = Hawk.get<String>(lastSearch)
}