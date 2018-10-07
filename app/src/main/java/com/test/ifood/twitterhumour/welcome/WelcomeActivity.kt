package com.test.ifood.twitterhumour.welcome

import android.os.Bundle
import com.test.ifood.twitterhumour.R
import com.test.ifood.twitterhumour.base.BaseActivity
import com.test.ifood.twitterhumour.databinding.ActivityWelcomeBinding
import com.test.ifood.twitterhumour.delegate.contentView
import com.test.ifood.twitterhumour.tweetlist.tweetsoverview.TweetListActivity
import com.test.ifood.twitterhumour.welcome.view.WelcomeView
import com.test.ifood.twitterhumour.welcome.viewmodel.WelcomeViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class WelcomeActivity : BaseActivity(), WelcomeView {

    @Inject
    lateinit var viewModel: WelcomeViewModel

    private val binding: ActivityWelcomeBinding by contentView(R.layout.activity_welcome)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }

    override fun findTweets() {
        compositeDisposable.add(viewModel.searchForTweets()
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
                        }))
    }

}
