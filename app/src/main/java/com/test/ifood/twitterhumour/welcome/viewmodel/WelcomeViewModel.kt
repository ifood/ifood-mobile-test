package com.test.ifood.twitterhumour.welcome.viewmodel

import android.app.Application
import android.databinding.Bindable
import android.view.View
import com.test.ifood.twitterhumour.BR
import com.test.ifood.twitterhumour.base.BaseViewModel
import com.test.ifood.twitterhumour.datasource.twitter.TwitterRepository
import com.test.ifood.twitterhumour.model.Tweet
import com.test.ifood.twitterhumour.welcome.view.WelcomeView
import io.reactivex.Flowable

class WelcomeViewModel(application: Application,
                       private val twitterRepository: TwitterRepository,
                       override var view: WelcomeView?) : BaseViewModel<WelcomeView>(application) {

    var userName: String = ""
        set(value) {
            if (field != value) {
                field = value
                registry.notifyChange(this, BR._all)
            }
        }

    val buttonEnabled: Boolean
        @Bindable get() {
            return !userName.isEmpty()
        }

    fun onSearchClicked(view: View) {
        this.view?.findTweets()
    }

    fun searchForTweets(): Flowable<List<Tweet>> {
        return twitterRepository.retrieveTweets(userName)
    }

}