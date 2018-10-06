package com.test.ifood.twitterhumour.welcome.viewmodel

import android.app.Application
import android.databinding.Bindable
import android.databinding.ObservableInt
import android.view.View
import com.test.ifood.twitterhumour.BR
import com.test.ifood.twitterhumour.base.BaseViewModel
import com.test.ifood.twitterhumour.datasource.twitter.TwitterRepository
import com.test.ifood.twitterhumour.model.Tweet
import com.test.ifood.twitterhumour.welcome.view.WelcomeView
import io.reactivex.Flowable
import kotlin.properties.Delegates.observable

class WelcomeViewModel(application: Application,
                       private val twitterRepository: TwitterRepository,
                       override var view: WelcomeView?) : BaseViewModel<WelcomeView>(application) {

    @get:Bindable
    var userName by observable(" ") {
        _, _, _ -> registry.notifyChange(this, BR.userName)
    }

    val buttonEnabled: Boolean
        @Bindable("userName")
        get() = !userName.isEmpty()

    val loadingVisible = ObservableInt(View.GONE)

    fun onSearchClicked(view: View) {
        updateLoadingState(true)
        this.view?.findTweets()
    }

    fun searchForTweets(): Flowable<List<Tweet>> {
        return twitterRepository.retrieveTweets(userName)
    }

    fun updateLoadingState(showLoading: Boolean) {
        loadingVisible.set(if (showLoading) View.VISIBLE else View.GONE)
    }

}