package br.com.andreyneto.ifood_mobile_test.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.andreyneto.ifood_mobile_test.data.TweetRepository
import br.com.andreyneto.ifood_mobile_test.data.database.TweetEntry

class SentimentViewModel(private val mRepository: TweetRepository, private val tweetID: Long) : ViewModel() {

    private val mTweet: LiveData<TweetEntry> = mRepository.getTweetByID(tweetID)

    fun getTweet(): LiveData<TweetEntry> {
        return mTweet
    }

    fun getSentiment(text: String): MutableLiveData<Float> {
        return mRepository.loadSentiment(text)
    }

    fun update(tweet: TweetEntry) {
        mRepository.updateTweet(tweet)
    }

    fun loadingSentiment(): MutableLiveData<Boolean> {
        return mRepository.loadingSentiment()
    }
}