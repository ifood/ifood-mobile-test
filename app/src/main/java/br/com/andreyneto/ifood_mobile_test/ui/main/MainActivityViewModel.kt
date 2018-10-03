package br.com.andreyneto.ifood_mobile_test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.andreyneto.ifood_mobile_test.data.TweetRepository
import br.com.andreyneto.ifood_mobile_test.data.database.TweetEntry

internal class MainActivityViewModel(private val mRepository: TweetRepository) : ViewModel() {

    fun getTweets(username: String): LiveData<List<TweetEntry>> {
        return mRepository.getTweetByUser(username)
    }

    fun loadingTweets(): MutableLiveData<Boolean> {
        return mRepository.loadingTweets()
    }

}