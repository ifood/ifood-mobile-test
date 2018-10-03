package br.com.andreyneto.ifood_mobile_test.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.andreyneto.ifood_mobile_test.data.TweetRepository

class SentimentViewModelFactory(private val mRepository: TweetRepository, private val tweetID: Long):
        ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SentimentViewModel(mRepository, tweetID) as T
    }
}