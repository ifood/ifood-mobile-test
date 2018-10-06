package com.test.ifood.twitterhumour.welcome

import android.os.Bundle
import android.util.Log
import com.test.ifood.twitterhumour.R
import com.test.ifood.twitterhumour.base.BaseActivity
import com.test.ifood.twitterhumour.databinding.ActivityWelcomeBinding
import com.test.ifood.twitterhumour.delegate.contentView
import com.test.ifood.twitterhumour.tweetlist.TweetListActivity
import com.test.ifood.twitterhumour.welcome.view.WelcomeView
import com.test.ifood.twitterhumour.welcome.viewmodel.WelcomeViewModel
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WelcomeActivity : BaseActivity(), WelcomeView {

    @Inject
    lateinit var viewModel: WelcomeViewModel

    private val binding: ActivityWelcomeBinding by contentView(R.layout.activity_welcome)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }

    override fun findTweets() {
        viewModel.searchForTweets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            viewModel.updateLoadingState(false)
                            startActivity(TweetListActivity.newIntent(this, it))
                        },
                        {
                            viewModel.updateLoadingState(false)
                            showErrorDialog(R.string.welcome_error_no_tweets)
                        })
    }

}
