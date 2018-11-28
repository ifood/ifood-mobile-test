package com.eblushe.apptwitter.features.userdetails.views

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.eblushe.apptwitter.R
import com.eblushe.apptwitter.common.extensions.toast
import com.eblushe.apptwitter.common.models.DataHolder
import com.eblushe.apptwitter.common.models.Tweet
import com.eblushe.apptwitter.common.views.BaseActivity
import com.eblushe.apptwitter.features.userdetails.adapters.TweetAdapter
import com.eblushe.apptwitter.features.userdetails.viewmodels.UserDetailsViewModel
import kotlinx.android.synthetic.main.activity_user_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.UnknownHostException

class UserDetailsActivity : BaseActivity<UserDetailsViewModel>() {
    override val viewModel: UserDetailsViewModel by viewModel()
    private var screenName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onLoadUI()
        onLoadLiveData(viewModel)

        screenName = getParam(PARAMS_SCREEN_NAME) { bundle, key -> bundle.getString(key) }
        if (screenName.isNullOrBlank()) {
            throw Throwable("PARAMS_SCREEN_NAME not found")
        }

        screenName?.let {
            viewModel.requestTweetsFromScreenName(it)
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_user_details
    }

    override fun onLoadUI() {

    }

    override fun onLoadLiveData(viewModel: UserDetailsViewModel) {
        viewModel.tweetsLiveData.observe(this, Observer(::observeTweets))
        viewModel.tweetsChangedLiveData.observe(this, Observer { tweets ->
            tweets.forEach { (position, tweet) ->
                tweetsRecyclerView.adapter?.notifyItemChanged(position, tweet)
            }
            tweets.clear()
           }
        )
    }

    private fun observeTweets(holder: DataHolder<List<Tweet>>) {
        when(holder.state) {
            DataHolder.State.LOADING -> { showLoading(progressBar) }
            DataHolder.State.LOADED,
            DataHolder.State.EMPTY,
            DataHolder.State.FINISHED -> { onTweetsLoaded(holder) }
            DataHolder.State.ERROR -> { onTweetsError(holder.error!!) }
            DataHolder.State.RELOADING -> {}
        }
    }

    private fun onTweetsLoaded(holder: DataHolder<List<Tweet>>) {
        hideLoading(progressBar)
        holder.state = DataHolder.State.FINISHED
        holder.value?.let { onLoadRecyclerView(it) }
    }

    private fun onTweetsError(exception: Exception) {
        hideLoading(progressBar)
        val cause = exception.cause
        when (cause) {
            is UnknownHostException -> {
                showSnackBar(rootContainer, R.string.check_your_connection, R.string.retry) {
                    screenName?.let { name -> viewModel.requestTweetsFromScreenName(name)}
                }
            }
            else -> {
                showSnackBar(rootContainer, R.string.generic_error, R.string.retry) {
                    screenName?.let { name -> viewModel.requestTweetsFromScreenName(name) }
                }
            }
        }
    }

    private fun onLoadRecyclerView(tweets: List<Tweet>) {
        var adapter = tweetsRecyclerView.adapter

        if (adapter == null) {
            adapter = TweetAdapter(tweets)
            adapter.onItemClick = { tweet, position ->
                toast(getString(R.string.analyzing))
                viewModel.requestTweetFeeling(tweet, position)
            }
        }

        if (adapter is TweetAdapter) {
            adapter.items = tweets
        }

        tweetsRecyclerView.layoutManager = LinearLayoutManager(this)
        tweetsRecyclerView.adapter = adapter
    }

    companion object {
        const val PARAMS_SCREEN_NAME = "PARAMS_SCREEN_NAME"
    }
}
